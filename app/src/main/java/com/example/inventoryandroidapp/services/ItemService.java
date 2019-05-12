package com.example.inventoryandroidapp.services;

import com.example.inventoryandroidapp.models.Item;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ItemService {
    @GET("item")
    Call<List<Item>> getItems();

    @GET("item/{id}")
    Call<Item> getItem(@Path("id")int id);

    @POST("item")
    Call<Item> createItem(@Body Item newItem);
}
