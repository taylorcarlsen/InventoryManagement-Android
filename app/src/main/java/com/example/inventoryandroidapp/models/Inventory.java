package com.example.inventoryandroidapp.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Inventory {
    private UUID Id;
    private int Qty;
    private int ItemId;
    private int UserId;
    private LocalDateTime Date;

    public Inventory(){

    }
//Getters
    public UUID getId() {
        return Id;
    }

    public int getQty() {
        return Qty;
    }

    public int getUserId() {
        return UserId;
    }

    public int getItemId() {
        return ItemId;
    }

    public LocalDateTime getDate() { return Date; }

    //Setters


    public void setId(UUID id) {
        Id = id;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }
}
