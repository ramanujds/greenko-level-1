package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetHealth;
import com.greenko.assetmanagement.model.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AssetRepository extends JpaRepository<Asset, String> {

    List<Asset> findByAssetName(String assetName);

    List<Asset> findByStatus(AssetStatus status);

    List<Asset> findByHealth(AssetHealth health);

    List<Asset> findByStatusAndAssetName(AssetStatus status, String assetName);

    @Query("from Asset where extract(year from installedDate) =:year")
    List<Asset> findByYear(int year);


}
