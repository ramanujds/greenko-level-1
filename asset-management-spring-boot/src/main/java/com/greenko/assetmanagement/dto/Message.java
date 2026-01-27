package com.greenko.assetmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record Message(String message, LocalDate date, LocalTime time) {
}
