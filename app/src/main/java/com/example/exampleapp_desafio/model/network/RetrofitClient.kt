package com.example.exampleapp_desafio.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val URL_BASE = "https://farmanet.minsal.cl/index.php/ws/"

        fun getRetrofitClient() : DrugStoreAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(DrugStoreAPI::class.java)
        }
    }
}