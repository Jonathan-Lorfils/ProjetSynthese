package com.example.projetnft.controller;

import com.example.projetnft.model.Admin;
import com.example.projetnft.service.AdminService;
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

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    private Admin admin;

    @BeforeEach
    void setup(){
        admin = Admin.builder()
                .id(1)
                .username("admin")
                .password("12345")
                .build();
    }

    @Test
    public void loginAdminTest() throws Exception {
        when(adminService.adminLogin(admin.getUsername(), admin.getPassword())).thenReturn(Optional.of(admin));

        MvcResult result = mockMvc.perform(get("/admin/{username}/{password}", admin.getUsername(), admin.getPassword())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualAdmin = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Admin.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualAdmin).isEqualTo(admin);
    }
}
