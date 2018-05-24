package org.zerhusen.Controllers.PauController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.GroupCategory;
import org.zerhusen.Repositories.PauRepositories.GroupCategoryRepository;


import java.util.Optional;

@RestController
@RequestMapping("/api/group")
public class GroupCategoryController {

    @Autowired
    private GroupCategoryRepository groupCategoryRepository;


    @GetMapping
    public ResponseEntity getAllGroupCategory(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(groupCategoryRepository.findAll());
        String[] filterCategory = {"idCategory", "category", "links"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Category", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCategory));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneGroupCategory(@PathVariable int id){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(groupCategoryRepository.findById(id));
        String[] filterCategory = {"idCategory", "category", "createAt","updateAt","idGroupCategory"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Category", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCategory));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @PostMapping("/new")
    public void createGroupCategory(@RequestBody GroupCategory groupCategory){
        groupCategoryRepository.save(groupCategory);
    }

    @PutMapping("/update")
    public void updateGroupCategory(@RequestBody GroupCategory groupCategory){
         groupCategoryRepository.save(groupCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GroupCategory> deleteGroupCategory(@PathVariable int id){
        groupCategoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
