package com.example.projetnft.service;

import com.example.projetnft.model.Customer;
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
import java.util.ArrayList;
import java.util.List;
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

    private Customer customer;

    private MultipartFile multipartFile;

    @BeforeEach
    void setup(){
        nft = Nft.nftBuilder()
                .certified(true)
                .toSell(false)
                .data("test".getBytes(StandardCharsets.UTF_8))
                .name("test")
                .price(0.0)
                .owner(null)
                .build();

        customer = Customer.customerBuilder()
                .id(1)
                .email("test@gmail.com")
                .firstName("Jojo")
                .lastName("Lolo")
                .password("12345")
                .phone("5141234321")
                .solde(32.0)
                .walletAddress("ajbdgoge2o8gojn309")
                .build();
    }

    @Test
    public void testCreateNft(){
        multipartFile = new MockMultipartFile("uploadFile","test:0",null,"test".getBytes(StandardCharsets.UTF_8));
        when(nftRepository.save(nft)).thenReturn(nft);
        Optional<Nft> actualNft = nftService.createNft(multipartFile);
        assertThat(actualNft.get()).isEqualTo(nft);
    }

    @Test
    public void testGetAllCustomersWaitingForCertification() {
        when(nftRepository.getAllByCertified(false)).thenReturn(getListOfNfts());
        Optional<List<Nft>> actualListOfNfts = nftService.getAllNftsWaitingForCertification();
        assertThat(actualListOfNfts.get().size()).isEqualTo(3);
        assertThat(actualListOfNfts.get().get(0).isCertified()).isEqualTo(false);
    }

    @Test
    public void testCertifiedNft(){
        when(nftRepository.findById(0)).thenReturn(Optional.of(nft));
        Optional<Nft> actualNft = nftService.certifiedNft(0);
        assertThat(actualNft.get().isCertified()).isEqualTo(true);
    }

    @Test
    public void testDeleteNftById(){
        Optional<Boolean> actualBool = nftService.deleteNftById(0);
        assertThat(actualBool.get()).isEqualTo(true);
    }

    @Test
    public void testGetAllNftsByOwner() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(nftRepository.getAllByOwnerAndCertifiedIsTrue(customer)).thenReturn(getListOfNfts());
        Optional<List<Nft>> actualListOfNfts = nftService.getAllNftByOwner(1);
        assertThat(actualListOfNfts.get().size()).isEqualTo(3);
    }

    @Test
    public void testGetAllCertifiedNftsToSell() {
        when(nftRepository.getAllByCertifiedIsTrueAndToSellIsTrue()).thenReturn(getListOfNfts());
        Optional<List<Nft>> actualListOfNfts = nftService.getAllCertifiedNftsToSell();
        assertThat(actualListOfNfts.get().size()).isEqualTo(3);
    }

    @Test
    public void testSetNftToSell(){
        when(nftRepository.findById(0)).thenReturn(Optional.of(nft));
        Optional<Nft> actualNft = nftService.setNftToSell(0, true);
        assertThat(actualNft.get().isToSell()).isEqualTo(true);
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
