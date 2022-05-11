package com.example.projetnft.controller;

import com.example.projetnft.model.Admin;
import com.example.projetnft.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Admin> loginAdmin(@PathVariable String username, @PathVariable String password){
        return adminService.adminLogin(username, password)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Admin()));
    }
}