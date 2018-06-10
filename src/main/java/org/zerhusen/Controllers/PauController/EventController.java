package org.zerhusen.Controllers.PauController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Base64Image.UtilBase64Image;
import org.zerhusen.Entities.PauEntities.Events;
import org.zerhusen.Repositories.PauRepositories.EventRepository;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Date;
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
        if (events.getImage() == null || events.getImage().equals("")){
            events.setImage("/images/event/default.jpg");
        }else {
            int nameImage = (int) (new Date().getTime()/1000);
            String path = "./src/main/resources/static/images/event/" + nameImage + ".jpg";
            UtilBase64Image.decoder(events.getImage(), path);
            events.setImage("/images/event/"+nameImage+".jpg");
        }
        eventRepository.save(events);
        ResponseEntity.ok().build();

    }

    @PutMapping("/update")
    public void updateEvents(@RequestBody Events events){
        Optional<Events> oldEvent = eventRepository.findById(events.getIdEvent());
        try
        {
            if (events.getImage() == null || events.getImage().equals("") || events.getImage().equals(UtilBase64Image.encoder("./src/main/resources/static/images/trainer/default.jpg"))){
                events.setImage(oldEvent.get().getImage());
            }else {
                if (!oldEvent.get().getImage().equals("/images/events/default.jpg"))
                    Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldEvent.get().getImage()));
                int nameImage = (int) (new Date().getTime() / 1000);
                String path = "./src/main/resources/static/images/events/" + nameImage + ".jpg";
                UtilBase64Image.decoder(events.getImage(), path);
                events.setImage("/images/events/" + nameImage + ".jpg");
            }
            eventRepository.save(events);
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
    public ResponseEntity<Events> deleteEvents(@PathVariable int id){
        try {
            Optional<Events> oldEvent = eventRepository.findById(id);
            Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldEvent.get().getImage()));
        } catch(NoSuchFileException e)
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
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
