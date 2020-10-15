package com.example.exampleapp_desafio.network

import android.util.Log
import com.example.exampleapp_desafio.model.network.DrugStoreAPI
import com.example.exampleapp_desafio.model.pojo.DrugStore
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var drugStoreAPI : DrugStoreAPI

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        drugStoreAPI =  retro.create(com.example.exampleapp_desafio.model.network.DrugStoreAPI::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllDrugstore_happy_case() = runBlocking {
        //given
        val mresultList = listOf<DrugStore>(DrugStore("LA CALERA", "2020-10-14","56","12","6"
            ,"miercoles","09:00:00","08:59:00","CARRERA 580",
            "21","-32.7872136780971", "-71.1894965921182", "DR. SIMI","+56332342337",
            "LA CALERA"))
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mresultList)))

        //when
        val result = drugStoreAPI.fetDataFromInternetCoroutines()
        //then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()
        val message = result.body()
        assertThat(message).hasSize(1)
        println(message.toString())
        assertThat(message?.get(0)?.comunaNombre?.contains("LA CALERA")).isTrue()
        val request = mMockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/getLocalesTurnos")
    }


}