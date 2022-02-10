package com.example.projetnft.controller;

import com.example.projetnft.model.User;
import com.example.projetnft.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

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
    }

    @Test
    public void registerUserTest() throws Exception {
        when(userService.registerUser(user)).thenReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(post("/user/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user))).andReturn();

        var actualUser = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    public void loginUserTest() throws Exception {
        when(userService.userLogin(user.getEmail(),user.getPassword())).thenReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(get("/user/{email}/{password}", user.getEmail(), user.getPassword())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualUser = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualUser).isEqualTo(user);
    }
}
