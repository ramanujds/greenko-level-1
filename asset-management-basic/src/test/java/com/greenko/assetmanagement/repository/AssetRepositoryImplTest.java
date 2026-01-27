package com.greenko.assetmanagement.repository;


import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetStatus;
import com.greenko.assetmanagement.model.Turbine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class AssetRepositoryImplTest {

    private AssetRepositoryImpl assetRepo;

    @BeforeEach
    void setup(){
        assetRepo = new AssetRepositoryImpl();
        Asset asset = new Turbine(UUID.randomUUID(),"Turbine 1",
                AssetStatus.ACTIVE, LocalDate.of(2020,10,10),3);
        assetRepo.getAssets().add(asset);
    }

    @AfterEach
    void cleanUp(){
        assetRepo = null;
    }


    @Test
    void testAddAsset(){

        // Arrange
        Asset asset = new Turbine(UUID.randomUUID(),"Turbine 2",
                AssetStatus.ACTIVE, LocalDate.of(2024,1,10),2);

        // Act
        assetRepo.addAsset(asset);

        // Assert
        Assertions.assertTrue(assetRepo.getAssets().contains(asset));


    }

//    @Test
//    void testRemoveAsset(){
//
//    }


}