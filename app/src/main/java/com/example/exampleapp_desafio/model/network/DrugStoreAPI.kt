package com.example.exampleapp_desafio.model.network

import com.example.exampleapp_desafio.model.pojo.DrugStore
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DrugStoreAPI {

    @GET("getLocalesTurnos")
     fun fetchFromInternet() : Call<List<DrugStore>>

    // This is with coroutines
    @GET("getLocalesTurnos")
    suspend fun fetDataFromInternetCoroutines() : Response<List<DrugStore>>

}