package com.example.exampleapp_desafio.model

import android.util.Log
import com.example.exampleapp_desafio.model.local.DrugStoreDao
import com.example.exampleapp_desafio.model.local.DrugStoreEntity
import com.example.exampleapp_desafio.model.network.RetrofitClient
import com.example.exampleapp_desafio.model.pojo.DrugStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrugStoreRepo(private val drugDao: DrugStoreDao) {

    private val service = RetrofitClient.getRetrofitClient()
    val allDataDrugStore = drugDao.getAllDrugStore()

    //la vieja confiable enqueue de retrofit
    fun getDataFromNetwork() {
        val call = service.fetchFromInternet()
        call.enqueue(object : Callback<List<DrugStore>> {
            override fun onResponse(
                call: Call<List<DrugStore>>,
                response: Response<List<DrugStore>>
            ) {
              when(response.code()) {
                  in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                      response.body()?.let {
                          drugDao.insertAllDrugStores(convert(it))
                      }
                  }
              }
            }
            override fun onFailure(call: Call<List<DrugStore>>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }
        })
    }

    //Coroutines with Retrofit
    fun getDataFromNetWorkCoroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetDataFromInternetCoroutines() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                    it.body()?.let {
                        drugDao.insertAllDrugStores(convert(it))
                    }
                }
            }
        }
        service.onFailure {
            Log.d("ERROR", it.message.toString())
        }
    }

    // Convert from pojo to Entity
    fun convert(listFromInternet: List<DrugStore>): List<DrugStoreEntity> {
        val listDrugEntity = mutableListOf<DrugStoreEntity>()
        listFromInternet.map {
            listDrugEntity.add(DrugStoreEntity(it.comunaNombre,
                it.funcionamientoDia,
                it.funcionamientoHoraApertura,
                it.funcionamientoHoraCierre,
                it.localDireccion,
                it.localId,
                it.localNombre,
                it.localTelefono,
                it.localidadNombre))
        }
        return listDrugEntity
    }

}