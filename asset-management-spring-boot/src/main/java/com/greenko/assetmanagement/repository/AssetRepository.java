package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetHealth;
import com.greenko.assetmanagement.model.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AssetRepository extends JpaRepository<Asset, String> {

    // JPQL
    @Query("from Asset where assetName=:assetName")
    Optional<Asset> searchByName(String assetName);


}
