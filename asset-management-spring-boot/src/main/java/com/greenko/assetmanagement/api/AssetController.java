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
    public List<Asset> findBy(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "status", required = false) String status){

        if(name!=null && status!=null){
            // search by both
        }

        else if(name!=null){
            // search by name
        }
        else if(status!=null){
            // search by status
        }

        return assetRepo.getAllAssets();

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
