package com.example.projetnft.service;

import com.example.projetnft.model.Admin;
import com.example.projetnft.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    public AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){this.adminRepository = adminRepository;}

    public Optional<Admin> adminLogin(String username, String password){
        try {
            return Optional.of(adminRepository.findByUsernameAndPassword(username, password));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

}
