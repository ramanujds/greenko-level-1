package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;

import java.util.ArrayList;
import java.util.List;

public class AssetRepositoryImpl implements AssetRepository{

    List<Asset> assets = new ArrayList<>();

    public Asset addAsset(Asset asset) {
        assets.add(asset);
        return asset;
    }

    public Asset findByName(String name) {
        for (var asset:assets){
            if(asset.getName().equalsIgnoreCase(name)){
                return asset;
            }
        }
        return null;
    }

    public List<Asset> findAllAssets() {
        return assets;
    }

    public void deleteAsset(String name) {
        var asset = findByName(name);
        assets.remove(asset);
    }
}
