package com.greenko.assetmanagement.api;

import com.greenko.assetmanagement.dto.AssetRequestDto;
import com.greenko.assetmanagement.dto.AssetResponseDto;
import com.greenko.assetmanagement.exception.AssetNotFoundException;
import com.greenko.assetmanagement.model.Asset;
import com.greenko.assetmanagement.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assets")
@CrossOrigin(origins = "http://localhost:4200")
public class AssetController {

    private AssetRepository assetRepo;

    public AssetController(AssetRepository assetRepo) {
        this.assetRepo = assetRepo;
    }

    @GetMapping
    public List<Asset> getAllAssets(){
       return assetRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<AssetResponseDto> saveAsset(@RequestBody AssetRequestDto assetDto){
        Asset asset = new Asset(
                UUID.randomUUID().toString(),
                assetDto.assetName(),
                assetDto.status(),
                assetDto.health(),
                assetDto.installedDate(),
                assetDto.location()
        );
        Asset saved = assetRepo.save(asset);
        AssetResponseDto response = new AssetResponseDto(
                saved.getAssetId(),
                saved.getAssetName(),
                saved.getStatus(),
                saved.getHealth(),
                saved.getInstalledDate(),
                saved.getLocation(),
                "Not Available"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/search")
    public List<Asset> findBy(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "status", required = false) String status,
                              @RequestParam(value = "year", required = false) Integer year){

        if(name!=null && status!=null){
            // search by both
        }

        else if(name!=null){
            // search by name
        }
        else if(status!=null){
            // search by status
        }

        else if (year!=null){
            return assetRepo.findByYear(year);
        }

        return assetRepo.findAll();

    }

    // update asset if it is present else throw an exception


    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable String id){
        return assetRepo.findById(id).orElseThrow(()->new AssetNotFoundException("Asset with ID:"+id+" Not found"));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAsset(@PathVariable String id){
        assetRepo.deleteById(id);
    }

}
