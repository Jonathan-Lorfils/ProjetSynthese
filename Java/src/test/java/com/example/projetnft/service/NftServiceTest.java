package com.example.projetnft.service;

import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.NftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NftServiceTest {

    @Mock
    private NftRepository nftRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
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
    public void testCreateNft(){
        multipartFile = new MockMultipartFile("uploadFile","test:0",null,"test".getBytes(StandardCharsets.UTF_8));
        when(nftRepository.save(nft)).thenReturn(nft);
        Optional<Nft> actualNft = nftService.createNft(multipartFile);
        assertThat(actualNft.get()).isEqualTo(nft);
    }
}
