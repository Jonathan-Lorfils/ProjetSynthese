package com.example.projetnft.controller;

import com.example.projetnft.model.Nft;
import com.example.projetnft.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/nft")
public class NftController {

    @Autowired
    private NftService nftService;

    @ResponseBody
    @PostMapping(value = "/uploadNft", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> uploadNft(@RequestParam(name="uploadFile") MultipartFile uploadFile) {
        return nftService.createNft(uploadFile)
                .map(document1 -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(false));
    }

    @GetMapping("/getAllNftsWaitingForCertification")
    public ResponseEntity<List<Nft>> getAllNftsWaitingForCertification(){
        return nftService.getAllNftsWaitingForCertification()
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("certifiedNft/{idNft}")
    public ResponseEntity<Nft> certifiedNft(@PathVariable Integer idNft){
        return nftService.certifiedNft(idNft)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Nft()));
    }

    @PutMapping("deleteNftById/{idNft}")
    public ResponseEntity<Boolean> deleteNftById(@PathVariable Integer idNft){
        return nftService.deleteNftById(idNft)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(false));
    }

    @GetMapping("getAllNftByOwner/{idOwner}")
    public ResponseEntity<List<Nft>> getAllNftByOwner(@PathVariable Integer idOwner){
        return nftService.getAllNftByOwner(idOwner)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("getAllCertifiedNftsToSell")
    public ResponseEntity<List<Nft>> getAllCertifiedNftsToSell(){
        return nftService.getAllCertifiedNftsToSell()
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("setNftToSell/{idNft}/{state}/{price}")
    public ResponseEntity<Nft> setNftToSell(@PathVariable Integer idNft, @PathVariable boolean state, @PathVariable double price){
        return nftService.setNftToSell(idNft, state, price)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Nft()));
    }
}
