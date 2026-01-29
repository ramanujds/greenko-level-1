package com.greenko.assetmanagement.dto;

import java.time.LocalDateTime;

public record ErrorResponse(String message,
                            int status,
                            LocalDateTime timestamp,
                            String error,
                            String path) {
}
