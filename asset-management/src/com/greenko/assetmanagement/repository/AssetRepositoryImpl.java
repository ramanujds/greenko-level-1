package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.exception.AssetNotFoundException;
import com.greenko.assetmanagement.model.*;

import java.util.ArrayList;
import java.util.List;

public class AssetRepositoryImpl implements AssetRepository{

    List<Asset> assets = new ArrayList<>();

    public void fixAssets() {
//        for (Asset asset:assets){
//            if (asset instanceof Turbine t){
//                t.replaceBlade();
//            } else if (asset instanceof SolarPanel s){
//                s.replaceCells();
//            }
//        }

        assets.forEach(asset->{
            switch (asset){
                case Turbine t -> t.replaceBlade();
                case SolarPanel s -> s.replaceCells();
                default -> System.out.println();
            }
        });

    }

    public void maintainTurbines() {
        assets.forEach(asset->{
            if (asset instanceof Turbine){
                asset.setStatus(AssetStatus.MAINTENANCE);
            }
        });
    }

    public Asset addAsset(Asset asset) {
        assets.add(asset);
        return asset;
    }

    public Asset findByName(String name) {
//        for (var asset:assets){
//            if(asset.getName().equalsIgnoreCase(name)){
//                return asset;
//            }
//        }
//        throw new AssetNotFoundException("Asset with name "+name+" not found");
        return assets.stream().filter(a->a.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(()->new AssetNotFoundException("Asset with name "+name+" not found"));

    }

    public List<Asset> findAllAssets() {
        return assets;
    }

    public void deleteAsset(String name) {
        assets.removeIf(asset->asset.getName().equalsIgnoreCase(name));
    }

    public List<Asset> findAssetsByStatus(AssetStatus status) {

        return assets.stream().filter(a->a.getStatus()==status).toList();
    }

    public List<Asset> findAssetsByHealth(AssetHealth health) {

        return List.of();
    }

    public List<Asset> findAssetByType(Asset type) {
        return List.of();
    }
}
