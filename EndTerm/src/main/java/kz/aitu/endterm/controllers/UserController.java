package kz.aitu.endterm.controllers;

import kz.aitu.endterm.models.User;
import kz.aitu.endterm.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/users/")
    public List<User> getAll(){
        return service.getAll();
    }

    @PostMapping("/register/")
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = service.create(user);
        if(createdUser == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }



}
