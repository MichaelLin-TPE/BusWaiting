package com.bus.buswaiting

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.bus.buswaiting.base.BaseActivity
import com.bus.buswaiting.base.GridSpacingItemDecoration
import com.bus.buswaiting.bean.CityData
import com.bus.buswaiting.databinding.ActivityMainBinding
import com.bus.buswaiting.tool.Tool
import com.bus.buswaiting.tool.Tool.toDp
import com.bus.buswaiting.ui.route.RouteNameActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = getViewModel(MainViewModel::class.java)
        initView()


        viewModel.etaLiveData.observe(this) { etaArrayList ->

        }
        viewModel.cityListLiveData.observe(this) { cityList ->
            Tool.logLongMessage("Michael", Gson().toJson(cityList))


            binding.list.apply {

                val manager = GridLayoutManager(this@MainActivity,3)
                layoutManager = manager
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        if (position == 0){
                            return 3
                        }
                        return 1
                    }
                }
                addItemDecoration(GridSpacingItemDecoration(3, 15.toDp(), true) { position ->
                    manager.spanSizeLookup.getSpanSize(position)
                })
                adapter = CityAdapter(cityList,object : CityAdapter.OnCityClickListener{
                    override fun onClickCity(data: CityData) {
                        viewModel.setOnCityClick(data)
                    }
                })
            }
        }

        viewModel.goToRouteNameActivityLiveData.observe(this){cityData->
            val intent = Intent(this@MainActivity, RouteNameActivity::class.java)
            intent.putExtra("city",cityData.cityId)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Tool.logLongMessage("Michael", "Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Tool.logLongMessage("Michael",token)

        })


    }



    private fun initView() {

    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}