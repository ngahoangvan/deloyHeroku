package org.zerhusen.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.model.security.UserAuthority;
import org.zerhusen.security.repository.UserAuthorityRepository;

@RestController
@RequestMapping("api/authUser")
public class UserAuthorityController {

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @GetMapping
    public Iterable<UserAuthority> getAllAuth(){
        return userAuthorityRepository.findAll();
    }
}
