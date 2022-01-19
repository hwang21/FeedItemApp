package com.fox.feeditemsapp.di

import com.fox.feeditemsapp.model.FeedItem
import com.fox.feeditemsapp.utils.ResponseState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideFeedItemStateFlow(): MutableStateFlow<ResponseState<List<FeedItem>>> {
        return MutableStateFlow(ResponseState.Empty)
    }
}