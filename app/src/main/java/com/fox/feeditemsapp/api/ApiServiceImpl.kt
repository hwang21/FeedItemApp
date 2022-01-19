package com.fox.feeditemsapp.api

import android.util.Log
import com.fox.feeditemsapp.utils.Component
import com.fox.feeditemsapp.model.FeedItem
import kotlin.random.Random

class ApiServiceImpl: ApiService {

    override suspend fun getFeedItems(): List<FeedItem>  = MockClient.getFeed()

    object MockClient {
        fun getFeed() = buildFeed()
        private fun buildFeed(): List<FeedItem> {
            val items = mutableListOf<FeedItem>()
            Component.values().forEach {
                val max = Random.nextInt(1, 11)
                for (i in 0..max) {
                    val item = FeedItem(it, "Headline #${i + 1} for $it", null)
                    items.add(item)
                }
            }
            Log.d("Items: ", items.toString())
            return items
        }
    }

}