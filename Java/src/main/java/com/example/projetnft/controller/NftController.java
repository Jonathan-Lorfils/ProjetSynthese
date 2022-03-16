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
    public ResponseEntity<Boolean> ReceiveNft(@RequestParam(name="uploadFile") MultipartFile uploadFile) {
        return nftService.createNft(uploadFile)
                .map(document1 -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(false));
    }

    @GetMapping("/getAllNftsWaitingForCertification")
    public ResponseEntity<List<Nft>> getAllCustomersWaitingForCertification(){
        return nftService.getAllNftsWaitingForCertification()
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("certifiedNft/{idNft}")
    public ResponseEntity<Nft> certifiedNft(@PathVariable Integer idNft){
        return nftService.certifiedNft(idNft)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(nft1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(new Nft()));
    }

    @GetMapping("deleteNftById/{idNft}")
    public ResponseEntity<Boolean> deleteNftById(@PathVariable Integer idNft){
        return nftService.deleteNftById(idNft)
                .map(nft1 -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(false));
    }
}
