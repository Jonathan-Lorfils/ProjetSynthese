package com.example.projetnft.service;

import com.example.projetnft.model.Admin;
import com.example.projetnft.model.Customer;
import com.example.projetnft.repository.AdminRepository;
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
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private  AdminService adminService;

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
    public void testLoginUser(){
        when(adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword())).thenReturn(admin);
        Optional<Admin> actualAdmin = adminService.adminLogin(admin.getUsername(), admin.getPassword());
        assertThat(actualAdmin.get()).isEqualTo(admin);
    }
}
