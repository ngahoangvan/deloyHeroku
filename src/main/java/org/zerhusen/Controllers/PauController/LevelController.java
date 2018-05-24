package org.zerhusen.Controllers.PauController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.Level;
import org.zerhusen.Repositories.PauRepositories.LevelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/level")
public class LevelController {

    @Autowired
    private LevelRepository levelRepository;


    @GetMapping
    public Iterable<Level> getAllLevel(){
        return levelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Level> getOneLevel(@PathVariable int id){
        return levelRepository.findById(id);
    }


    @PostMapping("/new")
    public void createLevel(@RequestBody Level level){
        levelRepository.save(level);
    }

    @PutMapping("/update")
    public void updateLevel(@RequestBody Level level){
        levelRepository.save(level);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Level> deleteLevel(@PathVariable int id){
        levelRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
