package com.example.projetnft.controller;

import com.example.projetnft.model.Cart;
import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Nft;
import com.example.projetnft.service.CartService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private Cart emptyCart;

    private Cart cart;

    @BeforeEach
    void setup(){
        List<Nft> nftListEmpty = new ArrayList<>();
        List<Nft> nftList = new ArrayList<>();

        emptyCart = Cart.cartBuilder()
                .id(1)
                .items(nftListEmpty)
                .totalprice(5)
                .build();

        cart = Cart.cartBuilder()
                .id(2)
                .items(nftList)
                .totalprice(5)
                .build();
    }

    @Test
    public void addItem() throws Exception {
        when(cartService.addItemToCart(1, 1)).thenReturn(Optional.of(cart));

        MvcResult result = mockMvc.perform(get("/cart/addItemToCart/{customerCartId}/{nftToAddId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCart = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Cart.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCart).isEqualTo(cart);
    }
}
