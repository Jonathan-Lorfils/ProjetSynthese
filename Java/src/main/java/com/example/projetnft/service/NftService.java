package com.example.projetnft.service;

import com.example.projetnft.model.Nft;
import com.example.projetnft.repository.CustomerRepository;
import com.example.projetnft.repository.NftRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class NftService {

    public NftRepository nftRepository;

    public CustomerRepository customerRepository;

    public NftService(NftRepository nftRepository, CustomerRepository customerRepository){
        this.nftRepository = nftRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Nft> createNft(MultipartFile multipartFile) {
        try {
            String[] signatureFile = java.net.URLDecoder.decode(multipartFile.getOriginalFilename(),
                    StandardCharsets.UTF_8).replace("\"","").split(":");
            Nft nft = new Nft();
            nft.setName(signatureFile[0]);
            nft.setData(multipartFile.getBytes());
            nft.setOwner(customerRepository.getById(Integer.parseInt(signatureFile[1])));
            return Optional.of(nftRepository.save(nft));
        } catch (Exception exception){
            return Optional.empty();
        }
    }

    public Optional<Nft> certifiedNft(Integer nftId){
        try {
            Nft nft = nftRepository.findById(nftId).get();
            nft.setCertified(true);
            nftRepository.save(nft);
            return Optional.of(nft);
        } catch (Exception exception){
            return Optional.empty();
        }
    }

    public Optional<List<Nft>> getAllNftsWaitingForCertification(){
        return Optional.of(nftRepository.getAllByCertified(false));
    }

    public Optional<Boolean> deleteNftById(Integer nftId){
        try {
            nftRepository.deleteById(nftId);
            return Optional.of(true);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
