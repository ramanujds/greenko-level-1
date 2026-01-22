package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.exception.AssetNotFoundException;
import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetHealth;
import com.greenko.assetmanagement.model.AssetStatus;

import java.util.List;
import java.util.Optional;

public interface AssetRepository {

    Asset addAsset(Asset asset);

    Asset findByName(String name) throws AssetNotFoundException;

    List<Asset> findAllAssets();

    void deleteAsset(String name) throws AssetNotFoundException;

    List<Asset> findAssetsByStatus(AssetStatus status);
    List<Asset> findAssetsByHealth(AssetHealth health);
    List<Asset> findAssetByType(Asset type);

    void maintainTurbines();

}
