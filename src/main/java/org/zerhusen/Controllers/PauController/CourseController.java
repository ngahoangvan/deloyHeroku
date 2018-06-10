package org.zerhusen.Controllers.PauController;



import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Base64Image.UtilBase64Image;
import org.zerhusen.Entities.PauEntities.Course;
import org.zerhusen.Repositories.PauRepositories.CourseRepository;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping
    public ResponseEntity getAllCourse(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(courseRepository.findAll(new Sort(Sort.Direction.DESC, "idCourse")));
        String[] filterCourse = {"idCourse", "name", "price","status",
            "image","description","details","dateStart","dateEnd",
            "createAt","idCategory"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Course", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCourse));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("{courseId}")
    public ResponseEntity getDetailOneCourse(@PathVariable int courseId){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(courseRepository.findById(courseId));
        String[] filterCourse = {"idCourse", "name", "price","status",
            "image","description","details","dateStart","dateEnd",
            "createAt","idCategory","trainers","listChapter"};
        String[] filterTrainer = {"idTrainer","firstName","lastName"};
        String[] filterChapter = {"idChapter","chapter"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Course", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCourse))
            .addFilter("filter.Trainer",SimpleBeanPropertyFilter.filterOutAllExcept(filterTrainer))
            .addFilter("filter.Chapter",SimpleBeanPropertyFilter.filterOutAllExcept(filterChapter));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("courseDESC/{page}/{size}")
    public ResponseEntity findTopTrainer(@PathVariable int page, @PathVariable int size){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(courseRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC,"idCourse")));
        String[] filterCourse = {"idCourse", "name", "description","image"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Course", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCourse));

        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long getCountCourse(){
        return courseRepository.countCourse();
    }


    @PostMapping("/new")
    public void createCourse(@RequestBody Course course){
        if (course.getImage() == null || course.getImage().equals("")){
            course.setImage("/images/course/default.jpg");
        }else {
            int nameImage = (int) (new Date().getTime()/1000);
            String path = "./src/main/resources/static/images/course/" + nameImage + ".jpg";
            UtilBase64Image.decoder(course.getImage(), path);
            course.setImage("/images/course/"+nameImage+".jpg");
        }
        courseRepository.save(course);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestBody Course course){
        Optional<Course> oldCourse = courseRepository.findById(course.getIdCourse());
        try
        {
            if (course.getImage() == null || course.getImage().equals("") || course.getImage().equals(UtilBase64Image.encoder("./src/main/resources/static/images/trainer/default.jpg"))){
                course.setImage(oldCourse.get().getImage());
            }else {
                if (!oldCourse.get().getImage().equals("/images/course/default.jpg"))
                    Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldCourse.get().getImage()));
                int nameImage = (int) (new Date().getTime() / 1000);
                String path = "./src/main/resources/static/images/course/" + nameImage + ".jpg";
                UtilBase64Image.decoder(course.getImage(), path);
                course.setImage("/images/course/" + nameImage + ".jpg");
            }
            courseRepository.save(course);
        }
        catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        try {
            Optional<Course> oldCourse = courseRepository.findById(id);
            Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldCourse.get().getImage()));
        } catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }
        courseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
