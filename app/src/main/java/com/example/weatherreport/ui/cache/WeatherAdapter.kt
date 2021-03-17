package com.example.weatherreport.ui.cache

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherreport.R
import com.example.weatherreport.data.weather.Weather
import kotlinx.android.synthetic.main.list_item_weather.view.*

class WeatherAdapter(
    private val items: MutableList<Weather>,
    private val onItemClick: (Weather) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.Holder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<Weather>) {
        val diffResult = DiffUtil.calculateDiff(WeatherDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class Holder(container: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate(
            R.layout.list_item_weather, container, false
        )
    ) {

        init {
            itemView.setOnClickListener {
                onItemClick(items[adapterPosition])
            }
        }

        fun bind(weather: Weather) {
            itemView.city.text = weather.cityName
            itemView.temperature.text = weather.temperature.toString()
        }
    }

    inner class WeatherDiffCallback(private val oldItems: List<Weather>, private val newItems: List<Weather>): DiffUtil.Callback() {
        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}