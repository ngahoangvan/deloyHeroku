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
import org.zerhusen.Entities.PauEntities.Trainer;
import org.zerhusen.Repositories.PauRepositories.LevelRepository;
import org.zerhusen.Repositories.PauRepositories.TrainerRepository;

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
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @PostMapping("/new")
    public void createTrainer(@RequestBody Trainer trainer){
        trainerRepository.save(trainer);
    }

    @PutMapping("/update")
    public void updateTrainer(@RequestBody Trainer trainer){
        trainerRepository.save(trainer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable int id){
            trainerRepository.deleteById(id);
            return ResponseEntity.ok().build();
    }



}
