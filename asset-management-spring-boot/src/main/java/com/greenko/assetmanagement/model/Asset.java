package com.greenko.assetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Asset {

    @Id
    private String assetId;
    @Column(unique = true, nullable = false)
    private String assetName;
    @Column(name = "asset_status")
    private AssetStatus status;
    private AssetHealth health;
    private LocalDate installedDate;
    @Column(length = 100)
    private String location;


}
