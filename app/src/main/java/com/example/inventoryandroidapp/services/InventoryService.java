package com.example.inventoryandroidapp.services;

import com.example.inventoryandroidapp.models.Inventory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InventoryService {
    @POST("inventory")
    Call<Inventory> createInventory(@Body Inventory newInventory);
}
