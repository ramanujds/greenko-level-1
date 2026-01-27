package com.greenko.assetmanagement.service;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.repository.AssetRepository;

public class AssetService {

    private AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public AssetRepository getAssetRepository() {
        return assetRepository;
    }

    public void setAssetRepository(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset getAssetByName(String name) {

        return assetRepository.findByName(name);
    }

}
