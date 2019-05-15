package com.example.inventoryandroidapp.models;

public class User {
    private int Id;
    private String FirstName;
    private String Password;

    public User(){
    }

    // Getters

    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getPassword() {
        return Password;
    }


    // Setters


    public void setId(int id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
