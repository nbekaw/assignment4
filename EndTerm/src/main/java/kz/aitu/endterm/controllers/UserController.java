package kz.aitu.endterm.controllers;

import kz.aitu.endterm.models.User;
import kz.aitu.endterm.services.UserService;
import kz.aitu.endterm.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    private final UserServiceInterface service;
    private final UserService userService;

    public UserController(UserServiceInterface service, UserService userService) {
        this.service = service;
        this.userService = userService;
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
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/withdraw/{senderUsername}/{ReceiverUsername}/{amount}")
    public ResponseEntity<String> withdrawMoneyBetweenUsers(@PathVariable String senderUsername, @PathVariable String ReceiverUsername, @PathVariable double amount) {
        User sender = userService.findByUsername(senderUsername);
        User receiver = userService.findByUsername(ReceiverUsername);

        if (sender == null || receiver == null) {
            return new ResponseEntity<>("Sender or receiver not found", HttpStatus.NOT_FOUND);
        }

        if (amount <= 0) {
            return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
        }

        if (sender.getBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
        }

        sender.withdraw(amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userService.update(sender);
        userService.update(receiver);

        return new ResponseEntity<>("Money transferred successfully", HttpStatus.OK);
    }
}


