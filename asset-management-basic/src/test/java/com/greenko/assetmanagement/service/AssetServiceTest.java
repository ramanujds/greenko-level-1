package com.greenko.assetmanagement.service;

import com.greenko.assetmanagement.exception.AssetNotFoundException;
import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetStatus;
import com.greenko.assetmanagement.model.Turbine;
import com.greenko.assetmanagement.repository.AssetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    AssetRepository assetRepository;

    @InjectMocks
    AssetService assetService;

    @Test
    void getAssetByName() {

        Asset asset = new Turbine(UUID.randomUUID(), "Turbine 1",
                AssetStatus.ACTIVE, LocalDate.of(2020, 10, 10), 3);

        Mockito.when(assetRepository.findByName(asset.getName())).thenReturn(asset);

        Assertions.assertEquals(asset, assetService.getAssetByName(asset.getName()));

        Mockito.verify(assetRepository, Mockito.times(1)).findByName(asset.getName());
    }

    @Test
    void getAssetByNameWhenNameNotPresent() {


        String name = "abc";
        Mockito.when(assetRepository.findByName(name)).thenThrow(AssetNotFoundException.class);

        Assertions.assertThrows(AssetNotFoundException.class, () -> assetService.getAssetByName(name));

        Mockito.verify(assetRepository, Mockito.times(1)).findByName(name);


    }

}