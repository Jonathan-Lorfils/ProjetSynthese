package com.example.projetnft.controller;

import com.example.projetnft.model.Orders;
import com.example.projetnft.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getAllOrdersByCustomer() throws Exception {
        when(orderService.getOrdersbyCustomer(1)).thenReturn(Optional.of(getListOfOrderss()));

        MvcResult result = mockMvc.perform(get("/orders/getAllOrdersByCustomer/{customerId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var actuals = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actuals.size()).isEqualTo(3);
    }

    private List<Orders> getListOfOrderss(){
        List<Orders> orderList = new ArrayList<>();

        orderList.add(Orders.orderBuilder()
                .id(1)
                .customer(null)
                .price(5)
                .build());

        orderList.add(Orders.orderBuilder()
                .id(2)
                .customer(null)
                .price(10)
                .build());

        orderList.add(Orders.orderBuilder()
                .id(3)
                .customer(null)
                .price(15)
                .build());
        return orderList;
    }
}
