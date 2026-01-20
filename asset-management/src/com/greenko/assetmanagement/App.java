package com.greenko.assetmanagement;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetStatus;
import com.greenko.assetmanagement.model.SolarPanel;
import com.greenko.assetmanagement.model.Turbine;

import java.time.LocalDate;
import java.util.UUID;

public class App {

    public static void main(String[] args) {

        Asset turbine1 = new Turbine(UUID.randomUUID(),
                "Small Turbine",
                AssetStatus.ACTIVE,
                LocalDate.of(2024, 10, 25),
                3);

        Asset solarPanel1 = new SolarPanel(UUID.randomUUID(),
                "Premium Solar Panel",
                AssetStatus.ACTIVE,
                LocalDate.of(2025,5,15),
                9.2);





    }

}
