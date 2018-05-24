package org.zerhusen.Controllers.PauController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.Chapter;
import org.zerhusen.Repositories.PauRepositories.ChapterRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping
    public ResponseEntity getAllChapter(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(chapterRepository.findAll());
        String[] filterChapter = {"idChapter","chapter","idCourse","listLesson"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Chapter", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterChapter));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllChapter(@PathVariable int id){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(chapterRepository.findById(id));
        String[] filterChapter = {"idChapter","chapter","idCourse","listLesson"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Chapter", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterChapter));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @PostMapping("/new")
    public void createChapter(@RequestBody Chapter chapter){
        chapterRepository.save(chapter);
    }

    @PutMapping("/update")
    public void updateChapter(@RequestBody Chapter chapter){
        chapterRepository.save(chapter);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable Integer id){
        chapterRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
