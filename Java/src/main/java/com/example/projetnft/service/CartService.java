package com.example.projetnft.service;

import com.example.projetnft.model.Cart;
import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CartRepository;
import com.example.projetnft.repository.NftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    public CartRepository cartRepository;

    public NftRepository nftRepository;

    public CartService(CartRepository cartRepository, NftRepository nftRepository){
        this.cartRepository = cartRepository;
        this.nftRepository = nftRepository;
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
}
