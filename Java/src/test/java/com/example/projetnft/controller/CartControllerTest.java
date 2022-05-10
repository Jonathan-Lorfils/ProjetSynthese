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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private Cart cart;

    private List<Nft> items;

    private Nft nft;

    @BeforeEach
    void setup(){

        nft = Nft.nftBuilder()
                .certified(true)
                .toSell(true)
                .data("test".getBytes(StandardCharsets.UTF_8))
                .name("test")
                .price(1)
                .owner(null)
                .build();

        items = new ArrayList<>();
        items.add(nft);

        cart = Cart.cartBuilder()
                .id(1)
                .totalprice(1)
                .items(items)
                .build();
    }

    @Test
    public void addItemTest() throws Exception {
        when(cartService.addItemToCart(1,1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/addItemToCart/{customerCartId}/{nftToAddId}", 1 , 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer).isEqualTo(true);
    }

    @Test
    public void removeItemTest() throws Exception {
        when(cartService.removeItemFromCart(1,1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/removeItemFromCart/{customerCartId}/{nftToAddId}", 1 , 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer).isEqualTo(true);
    }

    @Test
    public void getItemsFromCart() throws Exception {
        when(cartService.getItems(1)).thenReturn(Optional.of(items));

        MvcResult result = mockMvc.perform(get("/cart/getItems/{customerCartId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualList.size()).isEqualTo(1);
    }

    @Test
    public void getTotalPrice() throws Exception {
        when(cartService.getTotalPrice(1)).thenReturn(1.0);

        MvcResult result = mockMvc.perform(get("/cart/getTotalPrice/{customerCartId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualTotalPrice = new ObjectMapper().readValue(result.getResponse().getContentAsString(), double.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualTotalPrice).isEqualTo(1);
    }

    @Test
    public void validateCart() throws Exception {
        when(cartService.validateCart(1,1)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/cart/validateCart/{idNewOwner}/{customerCartId}", 1 , 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actualCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualCustomer).isEqualTo(true);
    }
}
