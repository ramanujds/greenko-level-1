package com.greenko.assetmanagement.api;

import com.greenko.assetmanagement.dto.AssetRequestDto;
import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.repository.AssetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private AssetRepository assetRepo = new AssetRepository();

    @GetMapping
    public List<Asset> getAllAssets(){
       return assetRepo.getAllAssets();
    }

    @PostMapping
    public Asset saveAsset(@RequestBody AssetRequestDto asset){
        return assetRepo.saveAsset(asset);
    }

    @GetMapping("/search")
    public Asset findByName(@RequestParam("name") String name){
        return assetRepo.findByName(name);
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable String id){
        return null;
    }

    // get asset by status

    // get asset by health

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable String id){
        assetRepo.deleteAsset(id);
    }

}
