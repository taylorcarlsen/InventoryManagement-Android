package com.example.inventoryandroidapp.services;

import com.example.inventoryandroidapp.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface UserService {
    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{id}")
    Call<User> getUser(@Path("id")int id);
}
