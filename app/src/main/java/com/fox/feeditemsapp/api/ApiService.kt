package com.fox.feeditemsapp.api

import com.fox.feeditemsapp.model.FeedItem
import retrofit2.http.GET

interface ApiService {

    @GET("feed/")
    suspend fun getFeedItems (): List<FeedItem>

}