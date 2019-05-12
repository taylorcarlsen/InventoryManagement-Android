package com.example.inventoryandroidapp.models;

public class Category {
    private String Id;
    private String Description;

    public Category(){

    }
    //Getter
    public String getId() {
        return Id;
    }

    public String getDescription() {
        return Description;
    }

    //Setter
    public void setId(String id) {
        Id = id;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
