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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


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
    public void addItemTest() throws Exception {
        when(cartService.addItemToCart(1, 1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/addItemToCart/{customerCartId}/{nftToAddId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCart = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCart).isEqualTo(true);
    }

    @Test
    public void removeItemTest() throws Exception {
        when(cartService.removeItemFromCart(1, 1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/removeItemFromCart/{customerCartId}/{nftToAddId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCart = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCart).isEqualTo(true);
    }

    @Test
    public void getTotalPriceTest() throws Exception {
        when(cartService.getTotalPrice(1)).thenReturn(5.0);

        MvcResult result = mockMvc.perform(get("/cart/getTotalPrice/{customerCartId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualTotalPrice = new ObjectMapper().readValue(result.getResponse().getContentAsString(), double.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualTotalPrice).isEqualTo(5.0);
    }

    @Test
    public void validateCartTest() throws Exception {
        when(cartService.validateCart(1,1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/validateCart/{idNewOwner}/{customerCartId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualTotalPrice = new ObjectMapper().readValue(result.getResponse().getContentAsString(), boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualTotalPrice).isEqualTo(true);
    }
}
