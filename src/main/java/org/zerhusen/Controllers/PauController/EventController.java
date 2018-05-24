package org.zerhusen.Controllers.PauController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.PauEntities.Events;
import org.zerhusen.Repositories.PauRepositories.EventRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @GetMapping
    public Iterable<Events> getAllEvents(){
        return eventRepository.findAll(new Sort(Sort.Direction.DESC, "idEvent"));
    }

    @GetMapping("/{id}")
    public Optional<Events> getOneEvents(@PathVariable int id){
        return eventRepository.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public Iterable<Events> getTopEvents(@PathVariable int page, @PathVariable int size){
        return eventRepository.findAll(new PageRequest(page,size, Sort.Direction.DESC, "idEvent"));
    }

    @GetMapping("/month/{num}")
    public Iterable<Events> getEventofMonth(@PathVariable int num){
        return eventRepository.getEventMonth(num);
    }

    @GetMapping("/count")
    public Long getCountNews(){
        return eventRepository.countEvents();
    }

    @PostMapping("/new")
    public void createEvents(@RequestBody Events events){
        eventRepository.save(events);
    }

    @PutMapping("/update")
    public void updateEvents(@RequestBody Events events){
        eventRepository.save(events);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Events> deleteEvents(@PathVariable int id){
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
