package com.greenko.assetmanagement.dto;

import com.greenko.assetmanagement.model.AssetHealth;
import com.greenko.assetmanagement.model.AssetStatus;

import java.time.LocalDate;

public record AssetRequestDto(
        String assetName,
        AssetStatus status,
        AssetHealth health,
        LocalDate installedDate,
        String location
) {
}
