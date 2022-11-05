package com.tunahanozatac.formula1apps.di

import android.content.Context
import androidx.room.Room
import com.tunahanozatac.formula1apps.data.source.local.dao.F1Dao
import com.tunahanozatac.formula1apps.data.source.local.database.F1Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): F1Database =
        Room.databaseBuilder(
            appContext,
            F1Database::class.java,
            "f1database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductFavoriteDAO(favoritesRoomDB: F1Database): F1Dao =
        favoritesRoomDB.listDao()
}