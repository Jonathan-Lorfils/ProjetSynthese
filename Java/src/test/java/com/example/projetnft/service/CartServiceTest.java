package com.example.projetnft.service;

import com.example.projetnft.model.Cart;
import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CartRepository;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.NftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private NftRepository nftRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;

    private Cart cartEmpty;

    private Nft nft;

    @BeforeEach
    void setup(){

        List<Nft> nftListEmpty = new ArrayList<>();
        List<Nft> nftList = new ArrayList<>();

        cartEmpty = Cart.cartBuilder()
                .id(1)
                .items(nftListEmpty)
                .totalprice(5)
                .build();

        cart = Cart.cartBuilder()
                .id(2)
                .items(nftList)
                .totalprice(5)
                .build();

        nft = Nft.nftBuilder()
                .certified(true)
                .toSell(false)
                .data("test".getBytes(StandardCharsets.UTF_8))
                .name("test")
                .price(0.1)
                .owner(null)
                .build();
    }

    @Test
    public void testAddItemToCart(){
        when(cartRepository.findById(1)).thenReturn(Optional.of(cartEmpty));
        when(nftRepository.findById(1)).thenReturn(Optional.of(nft));
        Optional<Cart> actualCart = cartService.addItemToCart(1,1);
        assertThat(actualCart.get().getItems().get(0)).isEqualTo(nft);
    }

    @Test
    public void testRemoveItemFromCart(){
        when(cartRepository.findById(2)).thenReturn(Optional.of(cart));
        when(nftRepository.findById(2)).thenReturn(Optional.of(nft));
        Optional<Cart> actualCart = cartService.removeItemFromCart(2,2);
        assertThat(actualCart.get().getItems().isEmpty()).isEqualTo(true);
    }

}
