package com.example.projetnft.repository;

import com.example.projetnft.model.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftRepository extends JpaRepository<Nft, Integer> {

    List<Nft> getAllByCertified(Boolean certificationState);
}
