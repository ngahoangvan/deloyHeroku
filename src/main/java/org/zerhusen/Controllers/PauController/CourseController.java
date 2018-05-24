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
import org.zerhusen.Entities.PauEntities.Course;
import org.zerhusen.Repositories.PauRepositories.CourseRepository;



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
        courseRepository.save(course);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestBody Course course){
        courseRepository.save(course);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        courseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
