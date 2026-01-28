package com.greenko.assetmanagement.repository;

import com.greenko.assetmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetRepository extends JpaRepository<Asset, String> {


}
