package com.greenko.assetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    private UUID assetId;
    private String assetName;
    private AssetStatus status;
    private AssetHealth health;
    private LocalDate installedDate;
    private String location;



}
