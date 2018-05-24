package org.zerhusen.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.Entities.Footer;
import org.zerhusen.Repositories.FooterRepository;
import org.zerhusen.security.JwtTokenUtil;

@RestController
@RequestMapping("api/footer")
public class FooterController {

    @Autowired
    private FooterRepository footerRepository;

    @GetMapping()
    public Iterable<Footer> getAllFooter(){
        return footerRepository.findAll();
    }

    @PostMapping("/new")
    public void createFooter(@RequestBody Footer footer){
        footerRepository.save(footer);
    }

    @PutMapping("/update")
    public void updateFooter(@RequestBody Footer footer){
        footerRepository.save(footer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Footer> deleteTitle(@PathVariable int id){
        footerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
