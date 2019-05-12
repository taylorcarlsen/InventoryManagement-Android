package com.example.inventoryandroidapp.services;

import android.text.GetChars;
import android.widget.ArrayAdapter;
import com.example.inventoryandroidapp.models.Category;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    @GET("category")
    Call<List<Category>> getCategories();

    /*@GET("category")
    ArrayList<Category> getCategories(); */
}
