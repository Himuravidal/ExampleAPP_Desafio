package com.example.exampleapp_desafio.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.exampleapp_desafio.model.local.DrugStoreDao
import com.example.exampleapp_desafio.model.local.DrugStoreDatabase
import com.example.exampleapp_desafio.model.local.DrugStoreEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var drugStoreDao: DrugStoreDao
    private lateinit var db: DrugStoreDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DrugStoreDatabase::class.java).build()
        drugStoreDao = db.getDrugStoreDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElements() = runBlocking {
        //given
        val drugStoreList = listOf(DrugStoreEntity("LA CALERA",
            "miercoles",
            "09:00:00",
            "08:59:00",
            "CARRERA 580","21", "DR. SIMI","+56332342337",
            "LA CALERA"))

        // when
        drugStoreDao.insertAllDrugStores(drugStoreList)

        //then
        drugStoreDao.getAllDrugStore().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].comunaNombre).isEqualTo("LA CALERA")

        }
    }

}