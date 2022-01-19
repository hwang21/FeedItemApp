package com.fox.feeditemsapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fox.feeditemsapp.databinding.RowLayoutBinding
import com.fox.feeditemsapp.utils.Component
import com.fox.feeditemsapp.model.FeedItem
import javax.inject.Inject

class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private var feeditems = emptyList<FeedItem>()

    class DataViewHolder(
        private val binding: RowLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedItem: FeedItem) {
              binding.root.setBackgroundColor(getColorForComponent(feedItem.component))
              binding.component.text = feedItem.component.toString()
              binding.headline.text = feedItem.headline
        }

        private fun getColorForComponent(component: Component): Int {
            return when(component) {
                Component.BIG_TOP -> Color.GREEN
                Component.RIVER -> Color.YELLOW
                Component.AD -> Color.BLUE
                Component.HOUSE_AD -> Color.CYAN
                Component.SLIDE_SHOW -> Color.LTGRAY
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = feeditems.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(feeditems[position])
    }

    fun setData(feedItems: List<FeedItem>) {
        this.feeditems = feedItems
        notifyDataSetChanged()
    }
}