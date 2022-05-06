package com.example.projetnft.service;

import com.example.projetnft.model.Cart;
import com.example.projetnft.model.Customer;
import com.example.projetnft.model.Nft;
import com.example.projetnft.model.Orders;
import com.example.projetnft.repository.CartRepository;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.NftRepository;
import com.example.projetnft.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    public CartRepository cartRepository;

    public NftRepository nftRepository;

    public CustomerRepository customerRepository;

    public OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, NftRepository nftRepository, CustomerRepository customerRepository, OrderRepository orderRepository){
        this.cartRepository = cartRepository;
        this.nftRepository = nftRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Boolean addItemToCart(Integer customerCartId, Integer nftToAddId){
        try {
            Cart customerCart = cartRepository.findById(customerCartId).get();
            Nft nftToAdd = nftRepository.findById(nftToAddId).get();
            customerCart.getItems().add(nftToAdd);
            cartRepository.save(updateTotalPrice(nftToAdd,customerCart,"+"));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean removeItemFromCart(Integer customerCartId, Integer nftToRemoveId){
        try {
            Cart customerCart = cartRepository.findById(customerCartId).get();
            Nft nftToRemove = nftRepository.findById(nftToRemoveId).get();
            customerCart.getItems().remove(nftToRemove);
            cartRepository.save(updateTotalPrice(nftToRemove,customerCart,"-"));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Cart updateTotalPrice(Nft nft, Cart cart, String operation){
        double currentTotalPrice = cart.getTotalprice();
        if (operation.equals("+")){
            cart.setTotalprice(currentTotalPrice + nft.getPrice());
            return cart;
        } else {
            cart.setTotalprice(currentTotalPrice - nft.getPrice());
            return cart;
        }
    }

    public double getTotalPrice(Integer customerCartId) {
        try{
            Cart customerCart = cartRepository.findById(customerCartId).get();
            return customerCart.getTotalprice();
        } catch (Exception exception){
            return -1;
        }
    }

    public Optional<List<Nft>> getItems(Integer customerCartId){
        try {
            Cart customerCart = cartRepository.findById(customerCartId).get();
            return Optional.of(customerCart.getItems());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Boolean validateCart(Integer idNewOwner, Integer customerCartId) {
        try {
            Cart cart = cartRepository.findById(customerCartId).get();
            Customer newOwner = customerRepository.findById(idNewOwner).get();
            if (reassignCartItems(newOwner, cart)) {
                return true;
            }
            return false;
        }catch (Exception exception) {
            return false;
        }
    }

    public Boolean reassignCartItems(Customer newOwner, Cart cart) {
        try {
            List<Nft> itemsInCart = cart.getItems();
            itemsInCart.forEach(nft -> reassignNft(nft, newOwner));
            if(createOrder(cart.getTotalprice(), newOwner)){
                resetCart(cart);
                return true;
            }
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean createOrder(double price, Customer customer) {
        try {
            Orders newOrder = new Orders();
            newOrder.setCustomer(customer);
            newOrder.setPrice(price);
            newOrder.setStatus("Completed");
            newOrder.setDate(getLocalDate());
            orderRepository.save(newOrder);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public LocalDate getLocalDate(){
        return java.time.LocalDate.now();
    }

    public Integer generateOrderNumber(){
        int min = 1000;
        int max = 9999;
        return (int) Math.random()*(max-min+1)+min;
    }

    public Boolean resetCart(Cart customerCart) {
        try{
            customerCart.setTotalprice(0);
            customerCart.getItems().clear();
            cartRepository.save(customerCart);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }

    private Boolean reassignNft(Nft nft, Customer newOwner){
        try {
            nft.setPrice(0);
            nft.setToSell(false);
            nft.setOwner(newOwner);
            nftRepository.save(nft);
            return true;
        } catch (Exception exception){
            return false;
        }
    }
}
