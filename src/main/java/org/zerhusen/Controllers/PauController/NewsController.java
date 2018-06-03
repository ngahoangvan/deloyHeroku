package org.zerhusen.Controllers.PauController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.News;
import org.zerhusen.Repositories.PauRepositories.NewsRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<News> getAllNews(){
        return newsRepository.findAll(new Sort(Sort.Direction.DESC, "idNews"));
    }

    @GetMapping("/{id}")
    public Optional<News> getOneNews(@PathVariable int id){
        return newsRepository.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public Iterable<News> getTopNew(@PathVariable int page, @PathVariable int size){
        return newsRepository.findAll(new PageRequest(page,size, Sort.Direction.DESC, "idNews"));
    }

    @GetMapping("/count")
    public Long getCountNews(){
        return newsRepository.countNews();
    }

    @PostMapping("/new")
    public void createNews(@RequestBody News events){
        newsRepository.save(events);
    }

    @PutMapping("/update")
    public void updateNews(@RequestBody News events){
        newsRepository.save(events);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable int id){
        newsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
