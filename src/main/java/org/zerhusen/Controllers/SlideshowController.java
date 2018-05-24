package org.zerhusen.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.Slideshow;
import org.zerhusen.Repositories.SlideshowRepository;

@RestController
@RequestMapping("api/slide")
public class SlideshowController {

    @Autowired
    private SlideshowRepository slideshowRepository;

    @GetMapping
    public Iterable<Slideshow> getAllSlider(){
        return slideshowRepository.findAll(new Sort(Sort.Direction.DESC,"idSlideshow"));
    }


    @PostMapping("/new")
    public void createSlider(@RequestBody Slideshow slideshow) {
        slideshowRepository.save(slideshow);
    }

    @PutMapping("/update")
    public void updateSlider(@RequestBody Slideshow slideshow) {
        slideshowRepository.save(slideshow);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Slideshow> deleteSlider(@PathVariable int id) {
        slideshowRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
