package com.greenko.assetmanagement.model;

import jakarta.persistence.*;


import java.time.LocalDate;


@Entity
public class Asset {

    @Id
    private String assetId;
    @Column(nullable = false)
    private String assetName;
    @Column(name = "asset_status")
    @Enumerated(EnumType.STRING)
    private AssetStatus status;
    @Enumerated(EnumType.STRING)
    private AssetHealth health;
    private LocalDate installedDate;
    @Column(length = 100)
    private String location;

    public Asset(String assetId, String assetName, AssetStatus status, AssetHealth health, LocalDate installedDate, String location) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.status = status;
        this.health = health;
        this.installedDate = installedDate;
        this.location = location;
    }

    public Asset() {
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public AssetHealth getHealth() {
        return health;
    }

    public void setHealth(AssetHealth health) {
        this.health = health;
    }

    public LocalDate getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(LocalDate installedDate) {
        this.installedDate = installedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
