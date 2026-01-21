package com.greenko.assetmanagement;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetStatus;
import com.greenko.assetmanagement.model.SolarPanel;
import com.greenko.assetmanagement.model.Turbine;
import com.greenko.assetmanagement.repository.AssetRepository;
import com.greenko.assetmanagement.repository.AssetRepositoryImpl;

import java.time.LocalDate;
import java.util.UUID;

public class App {

    public static void main(String[] args) {

        Asset turbine1 = new Turbine(UUID.randomUUID(),
                "Small Turbine",
                AssetStatus.ACTIVE,
                LocalDate.of(2024, 10, 25),
                3);

        AssetRepository assetRepo = new AssetRepositoryImpl();
        Asset solarPanel1 = new SolarPanel(UUID.randomUUID(),
                "Premium Solar Panel",
                AssetStatus.ACTIVE,
                LocalDate.of(2025,5,15),
                9.2);

        assetRepo.addAsset(solarPanel1);
        assetRepo.addAsset(turbine1);

        var asset = assetRepo.findByName("abc");
        System.out.println(asset);








    }

}
