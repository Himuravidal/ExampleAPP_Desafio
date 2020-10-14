package com.example.exampleapp_desafio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.exampleapp_desafio.model.DrugStoreRepo
import com.example.exampleapp_desafio.model.local.DrugStoreDatabase
import com.example.exampleapp_desafio.model.local.DrugStoreEntity

class DrugStoreViewModel(application: Application): AndroidViewModel(application) {

    private var drugStoreRepository: DrugStoreRepo
    val liveDatafromServer: LiveData<List<DrugStoreEntity>>

    init {
        val drugDao = DrugStoreDatabase.getDatabase(application).getDrugStoreDao()
        drugStoreRepository = DrugStoreRepo(drugDao)
        drugStoreRepository.getDataFromNetwork()
        liveDatafromServer = drugStoreRepository.allDataDrugStore
    }



}