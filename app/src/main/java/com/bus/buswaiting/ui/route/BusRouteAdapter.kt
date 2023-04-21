package com.bus.buswaiting.ui.route

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bus.buswaiting.bean.CityBusesData
import com.bus.buswaiting.databinding.ItemBusRouteLayoutBinding

class BusRouteAdapter(
    private val dataList: ArrayList<CityBusesData>,
    private val onBusRouteClickListener: OnBusRouteClickListener
) : RecyclerView.Adapter<BusRouteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusRouteLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onBusRouteClickListener)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    class ViewHolder(
        private val binding: ItemBusRouteLayoutBinding,
        private val onBusRouteClickListener: OnBusRouteClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cityBusesData: CityBusesData) {
            cityBusesData.routeName?.cnRouteName?.let {
                binding.busNumber.text = it
            }
            cityBusesData.subRoutes?.let {
                if (it.isNotEmpty()) {
                    binding.routeName.text = it[0].headSign
                }
            }
            binding.ivHeart.setOnClickListener {
                onBusRouteClickListener.onAddOrDeleteRoute(cityBusesData)
            }
            binding.routeImage.setOnClickListener {
                cityBusesData.routeMapImageUrl?.let {
                    onBusRouteClickListener.onImageClick(it)
                }
            }
            binding.rootView.setOnClickListener {
                onBusRouteClickListener.onRouteClick(cityBusesData)
            }
            val content = SpannableString("查看路線圖片")
            content.setSpan(UnderlineSpan(), 0, "查看路線圖片".length, 0)
            binding.routeImage.text = content
        }

    }

    interface OnBusRouteClickListener {
        fun onRouteClick(data: CityBusesData)
        fun onAddOrDeleteRoute(data: CityBusesData)
        fun onImageClick(url: String)
    }

}