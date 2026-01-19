package com.payease.app.model;

public class Wallet {

    private long walletId;
    private double balance;
    private UserProfile owner;

    public Wallet(long walletId, double balance, UserProfile owner) {
        this.walletId = walletId;
        this.balance = balance;
        this.owner = owner;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserProfile getOwner() {
        return owner;
    }

    public void setOwner(UserProfile owner) {
        this.owner = owner;
    }
}
