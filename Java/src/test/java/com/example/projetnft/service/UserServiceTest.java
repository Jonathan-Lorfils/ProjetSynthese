package com.example.projetnft.service;

import com.example.projetnft.model.User;
import com.example.projetnft.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private User userRegistred;

    @BeforeEach
    void setup(){
        user = User.builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .sellerCertification(true)
                .solde(32.004)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();

        userRegistred = User.builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .sellerCertification(true)
                .solde(32.004)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();
    }

    @Test
    public void testRegisterUser(){
        when(userRepository.save(user)).thenReturn(userRegistred);
        Optional<User> actualUser = userService.registerUser(user);
        assertThat(actualUser.get()).isEqualTo(userRegistred);
    }

    @Test
    public void testLoginUser(){
        when(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(user);
        Optional<User> actualUser = userService.userLogin(user.getEmail(),user.getPassword());
        assertThat(actualUser.get()).isEqualTo(user);
    }
}
