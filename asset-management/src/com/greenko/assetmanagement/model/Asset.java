package com.greenko.assetmanagement.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract sealed class Asset permits SolarPanel,Turbine {
    private UUID id;
    private String name;
    private AssetStatus status;
    public abstract AssetHealth evaluateHealth();
    private LocalDate installedDate;

    public Asset() {
    }


    public Asset(UUID id, String name, AssetStatus status, LocalDate installedDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.installedDate = installedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public LocalDate getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(LocalDate installedDate) {
        this.installedDate = installedDate;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", installedDate=" + installedDate +
                '}';
    }
}
