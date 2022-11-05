package com.tunahanozatac.formula1apps.di

import com.tunahanozatac.formula1apps.data.repository.FavRepositoryImpl
import com.tunahanozatac.formula1apps.domain.datasource.LocalDataSource
import com.tunahanozatac.formula1apps.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        localDataSource: LocalDataSource
    ): FavoriteRepository = FavRepositoryImpl(localDataSource)
}