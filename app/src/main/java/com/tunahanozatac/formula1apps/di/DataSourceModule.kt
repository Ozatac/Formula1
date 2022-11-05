package com.tunahanozatac.formula1apps.di

import com.tunahanozatac.formula1apps.data.source.local.LocalDataSourceImpl
import com.tunahanozatac.formula1apps.data.source.local.dao.F1Dao
import com.tunahanozatac.formula1apps.domain.datasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        productFavoriteDAO: F1Dao,
        ioDispatcher: CoroutineContext
    ): LocalDataSource = LocalDataSourceImpl(productFavoriteDAO, ioDispatcher)

}