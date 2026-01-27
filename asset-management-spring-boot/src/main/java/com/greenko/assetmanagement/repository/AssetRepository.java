package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;

import java.util.ArrayList;
import java.util.List;

public class AssetRepository {

    List<Asset> assets = new ArrayList<>();

    public Asset saveAsset(Asset asset){
        return null;
    }

    public List<Asset> getAllAssets(){
        return List.of();
    }

    public Asset findByName(String name){
        return null;
    }

    public void deleteAsset(String id){

    }



}
