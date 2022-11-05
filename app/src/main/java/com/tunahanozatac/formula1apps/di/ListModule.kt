package com.tunahanozatac.formula1apps.di

import com.tunahanozatac.formula1apps.data.network.ApiServices
import com.tunahanozatac.formula1apps.data.repository.DetailsRepositoryImp
import com.tunahanozatac.formula1apps.data.repository.ListRepositoryImp
import com.tunahanozatac.formula1apps.domain.repository.DetailsRepository
import com.tunahanozatac.formula1apps.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ListModule {

    @Singleton
    @Provides
    fun provideSongApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideListRepository(api: ApiServices): ListRepository {
        return ListRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(api: ApiServices): DetailsRepository {
        return DetailsRepositoryImp(api)
    }
}