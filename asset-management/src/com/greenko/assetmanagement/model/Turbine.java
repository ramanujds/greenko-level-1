package com.greenko.assetmanagement.model;

import java.time.LocalDate;
import java.util.UUID;

public class Turbine extends Asset {

    private int bladeCount;

    public Turbine(){ }

    public Turbine(int bladeCount) {
        this.bladeCount = bladeCount;
    }

    public Turbine(UUID id, String name, AssetStatus status, LocalDate installedDate, int bladeCount) {
        super(id, name, status, installedDate);
        this.bladeCount = bladeCount;
    }

    public int getBladeCount() {
        return bladeCount;
    }

    public void setBladeCount(int bladeCount) {
        this.bladeCount = bladeCount;
    }

    @Override
    public AssetHealth evaluateHealth() {
        return AssetHealth.GOOD;
    }

    @Override
    public String toString() {
        return "Turbine{" +
                "bladeCount=" + bladeCount +
                "} " + super.toString();
    }
}
