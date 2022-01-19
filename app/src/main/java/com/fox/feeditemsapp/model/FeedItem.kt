package com.fox.feeditemsapp.model

import com.fox.feeditemsapp.utils.Component

data class FeedItem(
    val component: Component,
    val headline: String,
    val imageUrl: String?
)
