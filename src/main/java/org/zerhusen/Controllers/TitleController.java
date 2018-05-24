package org.zerhusen.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.Title;
import org.zerhusen.Repositories.TitleRepository;

@RestController
@RequestMapping("api/title")
public class TitleController {
    @Autowired
    private TitleRepository titleRepository;

    @GetMapping()
    public Iterable<Title> getAllTitle(){
        return titleRepository.findAll();
    }


    @PostMapping("/new")
    public void createTitle(@RequestBody Title title){
        titleRepository.save(title);
    }

    @PutMapping("/update")
    public void updateTitle(@RequestBody Title title){
        titleRepository.save(title);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Title> deleteTitle(@PathVariable int id){
        titleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
