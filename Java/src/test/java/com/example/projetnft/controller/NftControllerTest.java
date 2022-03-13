package com.example.projetnft.controller;

import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.NftRepository;
import com.example.projetnft.service.NftService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebMvcTest(NftController.class)
public class NftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;

    @MockBean
    private NftService nftService;

    private Nft nft;

    private MultipartFile multipartFile;

    @BeforeEach
    void setup(){
        nft = Nft.nftBuilder()
                .certified(false)
                .toSell(false)
                .data("test".getBytes(StandardCharsets.UTF_8))
                .name("test")
                .price(0.0)
                .owner(null)
                .build();
    }

    @Test
    public void receiveDocumentTest() throws Exception {
        multipartFile = new MockMultipartFile("uploadFile", "test:0", null, "test".getBytes(StandardCharsets.UTF_8));
        when(nftService.createNft(multipartFile)).thenReturn(Optional.of(nft));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadNft")
                .file((MockMultipartFile) multipartFile))
                .andReturn();

        var actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualResponse).isEqualTo(true);
    }
}
