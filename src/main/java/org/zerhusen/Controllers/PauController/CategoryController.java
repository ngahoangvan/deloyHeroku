package org.zerhusen.Controllers.PauController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.Category;
import org.zerhusen.Repositories.PauRepositories.CategoryRepository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity getAllCategory(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(categoryRepository.findAll());
        String[] filterCategory = {"idCategory", "category", "createAt","updateAt"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Category", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCategory));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneCategory(@PathVariable int id){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(categoryRepository.findById(id));
        String[] filterCategory = {"idCategory", "category", "createAt","updateAt"};
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("filter.Category", SimpleBeanPropertyFilter
                .filterOutAllExcept(filterCategory));

        mappingJacksonValue.setFilters(filterProvider);
        return  new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }


    @PostMapping("/new")
    public void createCategory(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
