package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.dto.AssetRequestDto;
import com.greenko.assetmanagement.model.Asset;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssetRepository {

    List<Asset> assets = new ArrayList<>();

    public Asset saveAsset(AssetRequestDto asset){
        UUID id = UUID.randomUUID();
        Asset assetToSave = new Asset(id,
                asset.assetName(),
                asset.status(),
                asset.health(),
                asset.installedDate(),
                asset.location());
        assets.add(assetToSave);
        return assetToSave;
    }

    public List<Asset> getAllAssets(){
        return assets;
    }

    public Asset findByName(String name){
        return null;
    }

    public void deleteAsset(String id){

    }



}
