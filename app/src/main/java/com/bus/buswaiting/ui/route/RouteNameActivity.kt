package com.bus.buswaiting.ui.route

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bus.buswaiting.R
import com.bus.buswaiting.base.BaseActivity
import com.bus.buswaiting.base.SpacingItemDecoration
import com.bus.buswaiting.bean.CityBusesData
import com.bus.buswaiting.databinding.ActivityRouteNameBinding
import com.bus.buswaiting.tool.Tool
import com.bus.buswaiting.tool.Tool.toDp

class RouteNameActivity : BaseActivity() {

    private lateinit var binding: ActivityRouteNameBinding

    private lateinit var viewModel: RouteNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_name)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_route_name)
        viewModel = getViewModel(RouteNameViewModel::class.java)

        intent.extras?.let {

            viewModel.onCatchCityName(it.getString("city",""))

        }


        viewModel.busesDataListLiveData.observe(this){ dataList->

            binding.list.apply {
                addItemDecoration(SpacingItemDecoration(15.toDp(),dataList.size - 1))
                adapter = BusRouteAdapter(dataList,object : BusRouteAdapter.OnBusRouteClickListener{
                    override fun onRouteClick(data: CityBusesData) {

                    }

                    override fun onAddOrDeleteRoute(data: CityBusesData) {

                    }

                    override fun onImageClick(url: String) {
                        viewModel.onRouteImageClick(url)
                    }
                })
            }

        }

        viewModel.showRouteImageDialog.observe(this){url->
            Tool.logLongMessage("Michael","imageUrl : $url")
            val dialog = RouteImageDialog(url)
            dialog.show(supportFragmentManager,"dialog")
        }


    }
}