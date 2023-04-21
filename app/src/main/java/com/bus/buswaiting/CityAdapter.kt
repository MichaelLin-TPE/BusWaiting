package com.bus.buswaiting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bus.buswaiting.bean.CityData
import com.bus.buswaiting.databinding.ItemCityLayoutBinding
import com.bus.buswaiting.tool.Tool

class CityAdapter(private val cityList: ArrayList<CityData>,private val onCityClickListener: OnCityClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    companion object {
        private const val TITLE = 0
        private const val CITY = 1
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TITLE else CITY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TITLE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city_title_layout, parent, false)
            return TitleViewHolder(view)
        }
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCityLayoutBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding,onCityClickListener)
    }

    override fun getItemCount(): Int = cityList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityViewHolder) {
            holder.bind(cityList[position - 1])
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class CityViewHolder(private val binding: ItemCityLayoutBinding,private val onCityClickListener: OnCityClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CityData) {
            binding.cityData = data
            binding.root.background = Tool.getDrawable(data.resourceId)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onCityClickListener.onClickCity(data)
            }
        }
    }

    interface OnCityClickListener{
        fun onClickCity(data : CityData)
    }
}