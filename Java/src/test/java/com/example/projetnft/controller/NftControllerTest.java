package com.example.projetnft.controller;

import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.service.NftService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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
                .certified(true)
                .toSell(true)
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

    @Test
    public void getAllNftsWaitingForCertification() throws Exception {
        when(nftService.getAllNftsWaitingForCertification()).thenReturn(Optional.of(getListOfNfts()));

        MvcResult result = mockMvc.perform(get("/nft/getAllNftsWaitingForCertification")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals.size()).isEqualTo(3);
    }

    @Test
    public void certifiedNftTest() throws Exception {
        when(nftService.certifiedNft(0)).thenReturn(Optional.of(nft));

        MvcResult result = mockMvc.perform(get("/nft/certifiedNft/{idNft}", 0)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualNft = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Nft.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualNft.isCertified()).isEqualTo(true);
    }

    @Test
    public void getAllNftsByOwnerTest() throws Exception {
        when(nftService.getAllNftByOwner(1)).thenReturn(Optional.of(getListOfNfts()));

        MvcResult result = mockMvc.perform(get("/nft/getAllNftByOwner/{idNft}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals.size()).isEqualTo(3);
    }

    @Test
    public void getAllCertifiedNftsToSellTest() throws Exception {
        when(nftService.getAllCertifiedNftsToSell()).thenReturn(Optional.of(getListOfNfts()));

        MvcResult result = mockMvc.perform(get("/nft/getAllCertifiedNftsToSell")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals.size()).isEqualTo(3);
    }

    @Test
    public void setNftToSellTest() throws Exception {
        when(nftService.setNftToSell(0)).thenReturn(Optional.of(nft));

        MvcResult result = mockMvc.perform(get("/nft/setNftToSell/{idNft}", 0)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualNft = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Nft.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualNft.isToSell()).isEqualTo(true);
    }


    private List<Nft> getListOfNfts(){
        List<Nft> nftsList = new ArrayList<>();
        nftsList.add(Nft.nftBuilder()
                .id(1)
                .name("Nft1")
                .price(0.0)
                .certified(false)
                .data("nft1".getBytes(StandardCharsets.UTF_8))
                .toSell(false)
                .owner(null)
                .build());

        nftsList.add(Nft.nftBuilder()
                .id(2)
                .name("Nft2")
                .price(0.0)
                .certified(false)
                .data("nft2".getBytes(StandardCharsets.UTF_8))
                .toSell(false)
                .owner(null)
                .build());

        nftsList.add(Nft.nftBuilder()
                .id(3)
                .name("Nft3")
                .price(0.0)
                .certified(false)
                .data("nft3".getBytes(StandardCharsets.UTF_8))
                .toSell(false)
                .owner(null)
                .build());
        return nftsList;
    }
}
