package com.fox.feeditemsapp.di

import com.fox.feeditemsapp.api.ApiService
import com.fox.feeditemsapp.api.ApiServiceImpl
import com.fox.feeditemsapp.model.FeedItem
import com.fox.feeditemsapp.repository.Repository
import com.fox.feeditemsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    @Named("MockedApiService")
    fun provideMockApiService(): ApiService {
        return ApiServiceImpl()
    }

    @Singleton
    @Provides
    fun provideRepository(@Named("MockedApiService") apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Singleton
    @Provides
    fun provideFeedItems(): List<FeedItem> {
        return emptyList()
    }

}