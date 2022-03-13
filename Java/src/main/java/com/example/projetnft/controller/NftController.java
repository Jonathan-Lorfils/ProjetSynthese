package com.example.projetnft.controller;

import com.example.projetnft.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
}
