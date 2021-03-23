package com.monsun.suiicao.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {
    private String BASE_URL = "http://suii.somee.com";
    private Retrofit retrofit ;
    private ILocalServices services;
    public ApiInstance()
    {
        retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        services = retrofit.create(ILocalServices.class);
    }
    public ILocalServices getServices()
    {
        return services;
    }
}
