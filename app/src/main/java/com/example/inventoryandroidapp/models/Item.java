package com.example.inventoryandroidapp.models;

public class Item {
    private int Id;
    private String Name;
    private String Description;
    private int BaseQty;
    private String CategoryId;
    private String CategoryName;

    public Item(){

    }
    // Getters

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getBaseQty() {
        return BaseQty;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    // Setters

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setBaseQty(int baseQty) {
        BaseQty = baseQty;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
