package com.example.projetnft.service;

import com.example.projetnft.model.User;
import com.example.projetnft.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> registerUser(User user){
        try{
            return Optional.of(userRepository.save(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> userLogin(String email, String password){
        try {
            return Optional.of(userRepository.findByEmailAndPassword(email, password));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
