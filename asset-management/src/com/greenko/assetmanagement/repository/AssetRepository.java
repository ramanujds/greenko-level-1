package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;

import java.util.List;

public interface AssetRepository {

    Asset addAsset(Asset asset);

    Asset findByName(String name);

    List<Asset> findAllAssets();

    void deleteAsset(String name);


}
