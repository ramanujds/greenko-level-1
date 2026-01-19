package com.payease.app.model;

public class Wallet implements WalletOperations {

    private long walletId;
    private double balance;
    private Profile owner;

    public Wallet(long walletId, double balance, Profile owner) {
        this.walletId = walletId;
        this.balance = balance;
        this.owner = owner;
    }

    public long getWalletId() {
        return walletId;
    }

    // deposit <- amount
    // withdraw <- amount

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId=" + walletId +
                ", balance=" + balance +
                ", owner=" + owner +
                '}';
    }

    @Override
    public void deposit(double amount) {

    }

    @Override
    public void withdraw(double amount) {

    }
}
