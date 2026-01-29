package com.greenko.assetmanagement.exception;

public class AssetNotFoundException extends RuntimeException{

    public AssetNotFoundException() {
    }

    public AssetNotFoundException(String message) {
        super(message);
    }
}
