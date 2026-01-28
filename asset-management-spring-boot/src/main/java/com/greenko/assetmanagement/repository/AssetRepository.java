package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.model.AssetHealth;
import com.greenko.assetmanagement.model.AssetStatus;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AssetRepository extends JpaRepository<Asset, String> {

    List<Asset> findByAssetName(String assetName);

    List<Asset> findByStatus(AssetStatus status);


    List<Asset> findByStatusAndAssetName(AssetStatus status, String assetName);

    // @Query("from Asset where year(installedDate)=:year")
    // @Query("from Asset where extract(year from installedDate) =:year")
    @Query(value = "select * from asset where extract(year from installed_date) = :year", nativeQuery = true)
    List<Asset> findByYear(int year);

    @Query(name = "findByHealth")
    List<Asset> findByHealth(AssetHealth health);


}
