package com.example.exampleapp_desafio.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrugStoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrugStores(list: List<DrugStoreEntity>)

    @Query("SELECT * FROM drug_store")
    fun getAllDrugStore(): LiveData<List<DrugStoreEntity>>

}