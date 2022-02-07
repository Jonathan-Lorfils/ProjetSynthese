package com.example.projetnft.controller;

import com.example.projetnft.model.User;
import com.example.projetnft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return userService.registerUser(user)
                .map(user1 -> ResponseEntity.status(HttpStatus.CREATED).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<User> loginUser(@PathVariable String email, @PathVariable String password){
        return userService.userLogin(email, password)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new User()));
    }
}
