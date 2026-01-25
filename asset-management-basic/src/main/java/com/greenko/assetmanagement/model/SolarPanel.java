package com.greenko.assetmanagement.model;

import java.time.LocalDate;
import java.util.UUID;

public final class SolarPanel extends Asset {

    private double efficiencyRating;

    public void replaceCells(){
        System.out.println("Cells are replaced");
    }

    public SolarPanel(double efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public SolarPanel(UUID id, String name, AssetStatus status, LocalDate installedDate, double efficiencyRating) {
        super(id, name, status, installedDate);
        this.efficiencyRating = efficiencyRating;
    }

    public double getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(double efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    @Override
    public AssetHealth evaluateHealth() {
        return AssetHealth.GOOD;
    }

    @Override
    public String toString() {
        return "SolarPanel{" +
                "efficiencyRating=" + efficiencyRating +
                "} " + super.toString();
    }
}
