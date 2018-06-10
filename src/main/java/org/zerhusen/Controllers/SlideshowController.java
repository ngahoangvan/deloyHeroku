package org.zerhusen.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Base64Image.UtilBase64Image;
import org.zerhusen.Entities.Slideshow;
import org.zerhusen.Repositories.SlideshowRepository;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

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

        if (slideshow.getImage() == null || slideshow.getImage().equals("")){
            slideshow.setImage("/images/slideshow/default.jpg");
        }else {
            int nameImage = (int) (new Date().getTime()/1000);
            String path = "./src/main/resources/static/images/slideshow/" + nameImage + ".jpg";
            UtilBase64Image.decoder(slideshow.getImage(), path);
            slideshow.setImage("/images/slideshow/"+nameImage+".jpg");
        }
        slideshowRepository.save(slideshow);
        ResponseEntity.ok().build();

    }

    @PutMapping("/update")
    public void updateSlider(@RequestBody Slideshow slideshow) {
        Optional<Slideshow> oldSlides = slideshowRepository.findById(slideshow.getIdSlideshow());
        try
        {
            if (slideshow.getImage() == null || slideshow.getImage().equals("") || slideshow.getImage().equals(UtilBase64Image.encoder("./src/main/resources/static/images/trainer/default.jpg"))){
                slideshow.setImage(oldSlides.get().getImage());
            }else {
                if (!oldSlides.get().getImage().equals("/images/slideshow/default.jpg"))
                    Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldSlides.get().getImage()));
                int nameImage = (int) (new Date().getTime() / 1000);
                String path = "./src/main/resources/static/images/slideshow/" + nameImage + ".jpg";
                UtilBase64Image.decoder(slideshow.getImage(), path);
                slideshow.setImage("/images/slideshow/" + nameImage + ".jpg");
            }
            slideshowRepository.save(slideshow);
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
    public ResponseEntity<Slideshow> deleteSlider(@PathVariable int id) {
        try {
            Optional<Slideshow> oldSlides = slideshowRepository.findById(id);
            Files.deleteIfExists(Paths.get("./src/main/resources/static" + oldSlides.get().getImage()));
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
        slideshowRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
