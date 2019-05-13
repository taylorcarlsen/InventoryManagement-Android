package com.example.inventoryandroidapp.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder{
    private static String URL = "https://twcinventorytracker.azurewebsites.net/api/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S builderService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
