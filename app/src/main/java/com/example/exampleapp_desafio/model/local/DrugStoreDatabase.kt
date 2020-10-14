package com.example.exampleapp_desafio.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DrugStoreEntity::class], version = 1)
abstract class DrugStoreDatabase: RoomDatabase() {
    abstract fun getDrugStoreDao() : DrugStoreDao
    companion object {
        @Volatile
        private var INSTANCE: DrugStoreDatabase? = null

        fun getDatabase(context: Context): DrugStoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    DrugStoreDatabase::class.java,
                    "drugStoreDB" )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}