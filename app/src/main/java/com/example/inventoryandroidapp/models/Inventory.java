package com.example.inventoryandroidapp.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Inventory {
    private UUID Id;
    private int Quantity;
    private int UserId;
    private int ItemId;
    private LocalDateTime Date;

    public Inventory(){

    }
//Getters
    public UUID getId() {
        return Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getUserId() {
        return UserId;
    }

    public int getItemId() {
        return ItemId;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    //Setters

    public void setId(UUID id) {
        Id = id;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }
}
