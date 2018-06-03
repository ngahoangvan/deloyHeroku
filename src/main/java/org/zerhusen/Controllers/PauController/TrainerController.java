package org.zerhusen.Controllers.PauController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Base64Image.UtilBase64Image;
import org.zerhusen.Entities.PauEntities.Trainer;
import org.zerhusen.Repositories.PauRepositories.LevelRepository;
import org.zerhusen.Repositories.PauRepositories.TrainerRepository;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("api/trainer")
public class TrainerController {
    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping
    public ResponseEntity getAllTrainer(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(trainerRepository.findAll());
        String[] filterTrainer = {"idTrainer", "firstName","lastName", "image", "description", "status"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Trainer",SimpleBeanPropertyFilter
                .filterOutAllExcept(filterTrainer));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainerProfile(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(trainerRepository.findAll(new Sort(Sort.Direction.DESC, "idTrainer")));
        String[] filterTrainer = {"idTrainer", "firstName","lastName", "image", "description", "status"
                                    ,"birthday","phone","address","join","idCategory"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Trainer",SimpleBeanPropertyFilter
                .filterOutAllExcept(filterTrainer));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long getCountTrainer(){
        return trainerRepository.countTrainer();
    }

    @GetMapping("/top/{page}/{size}")
    public ResponseEntity getTopTrainer(@PathVariable int page, @PathVariable int size){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(trainerRepository.findAll(new PageRequest(page,size, Sort.Direction.DESC,"idTrainer")));
        String[] filterTrainer = {"idTrainer", "firstName","lastName", "image", "description", "status"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Trainer",SimpleBeanPropertyFilter
                .filterOutAllExcept(filterTrainer));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @GetMapping("/profile/{idTrainer}")
    public ResponseEntity getProfileOneTrainer(@PathVariable int idTrainer){

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(trainerRepository.findById(idTrainer));

        String[] filterTrainer = {"idTrainer", "firstName","lastName", "birthday", "phone",
                                    "address","image","description","join","status","courses","listLevel"};
        String[] filterCourse = {"idCourse", "name"};

        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Trainer",SimpleBeanPropertyFilter
                .filterOutAllExcept(filterTrainer))
            .addFilter("filter.Course", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCourse));

        mappingJacksonValue.setFilters(filterProvider);
        System.out.println(mappingJacksonValue);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @PostMapping("/new")
    public void createTrainer(@RequestBody Trainer trainer){
        if (trainer.getImage() == null || trainer.getImage().equals("")){
            trainer.setImage("/images/trainer/default.jpg");
        }else {
            int nameImage = (int) (new Date().getTime()/1000);
            String path = "./src/main/resources/static/images/trainer/" + nameImage + ".jpg";
            UtilBase64Image.decoder(trainer.getImage(), path);
            trainer.setImage("/images/trainer/"+nameImage+".jpg");
        }
        trainerRepository.save(trainer);
        ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public void updateTrainer(@RequestBody Trainer trainer){
        String oldTrainer = trainerRepository.findImageTrainer(trainer.getIdTrainer());
        String nameOldImage = oldTrainer.substring(15, oldTrainer.length()-4);
        try
        {
            if (trainer.getImage() == null || trainer.getImage().equals("") || trainer.getImage().equals(UtilBase64Image.encoder("./src/main/resources/static/images/trainer/default.jpg"))){
                trainer.setImage("/images/trainer/default.jpg");
            }else {
            Files.deleteIfExists(Paths.get("./src/main/resources/static/images/trainer/" + nameOldImage + ".jpg"));
            int nameImage = (int) (new Date().getTime()/1000);
            String path = "./src/main/resources/static/images/trainer/" + nameImage + ".jpg";
            UtilBase64Image.decoder(trainer.getImage(), path);
            trainer.setImage("/images/trainer/"+nameImage+".jpg");
            trainerRepository.save(trainer);
            }
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
    public ResponseEntity<?> deleteTrainer(@PathVariable int id){
            trainerRepository.deleteById(id);
            return ResponseEntity.ok().build();
    }



}
