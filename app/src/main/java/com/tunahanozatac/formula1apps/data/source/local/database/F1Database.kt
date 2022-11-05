package com.tunahanozatac.formula1apps.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tunahanozatac.formula1apps.data.source.local.dao.F1Dao
import com.tunahanozatac.formula1apps.domain.model.Item

@Database(entities = [Item::class], version = 1)
abstract class F1Database : RoomDatabase() {
    abstract fun listDao(): F1Dao

    companion object {
        @Volatile
        private var instance: F1Database? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, F1Database::class.java, "f1database"
        ).build()
    }
}