package com.fox.feeditemsapp.repository

import com.fox.feeditemsapp.api.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    fun getFeedItems() = flow {
        emit(apiService.getFeedItems())
    }
}