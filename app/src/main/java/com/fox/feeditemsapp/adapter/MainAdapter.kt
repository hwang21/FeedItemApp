package com.fox.feeditemsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fox.feeditemsapp.databinding.*
import com.fox.feeditemsapp.model.FeedItem
import javax.inject.Inject

class MainAdapter @Inject constructor(private var feedItems: List<FeedItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class BigTopViewHolder(
        private val binding: RowLayoutBigTopBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
              binding.component.text = feedItem.component.toString()
              binding.headline.text = feedItem.headline
        }
    }

    class RiverViewHolder(
        private val binding: RowLayoutRiverBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
            binding.component.text = feedItem.component.toString()
            binding.headline.text = feedItem.headline
        }
    }

    class ADViewHolder(
        private val binding: RowLayoutAdBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
            binding.component.text = feedItem.component.toString()
            binding.headline.text = feedItem.headline
        }
    }

    class HOUSEADViewHolder(
        private val binding: RowLayoutHouseAdBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
            binding.component.text = feedItem.component.toString()
            binding.headline.text = feedItem.headline
        }
    }
    class SlideShowViewHolder(
        private val binding: RowLayoutSlideShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
            binding.component.text = feedItem.component.toString()
            binding.headline.text = feedItem.headline
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
         return when(viewType) {
            BIG_TOP_TYPE ->
                BigTopViewHolder(RowLayoutBigTopBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            RIVER_TYPE ->
                RiverViewHolder(RowLayoutRiverBinding.inflate(LayoutInflater.from(parent.context), parent, false))
             AD_TYPE ->
                 ADViewHolder(RowLayoutAdBinding.inflate(LayoutInflater.from(parent.context), parent, false))
             HOUSE_AD_TYPE ->
                HOUSEADViewHolder(RowLayoutHouseAdBinding.inflate(LayoutInflater.from(parent.context), parent, false))
             else ->
                SlideShowViewHolder(RowLayoutSlideShowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
         }
    }


    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         when(feedItems[position].component.value) {
             BIG_TOP_TYPE -> (holder as BigTopViewHolder).bind(feedItems[position])
             RIVER_TYPE -> (holder as RiverViewHolder).bind(feedItems[position])
             AD_TYPE -> (holder as ADViewHolder).bind(feedItems[position])
             HOUSE_AD_TYPE -> (holder as HOUSEADViewHolder).bind(feedItems[position])
             SLIDE_SHOW_TYPE -> (holder as SlideShowViewHolder).bind(feedItems[position])
         }
    }

    override fun getItemViewType(position: Int): Int {
        return feedItems[position].component.value
    }

    fun setData(_feedItems: List<FeedItem>) {
        feedItems = _feedItems
        notifyDataSetChanged()
    }

    companion object {
        const val BIG_TOP_TYPE = 1
        const val RIVER_TYPE = 2
        const val AD_TYPE = 3
        const val HOUSE_AD_TYPE = 4
        const val SLIDE_SHOW_TYPE = 5
    }
}