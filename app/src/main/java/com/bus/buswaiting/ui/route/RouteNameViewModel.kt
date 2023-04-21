package com.bus.buswaiting.ui.route

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bus.buswaiting.base.BaseViewModel
import com.bus.buswaiting.bean.CityBusesData
import com.bus.buswaiting.http.ApiWrapper
import com.bus.buswaiting.tool.Tool
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RouteNameViewModel(application: Application) : BaseViewModel(application) {

    val busesDataListLiveData = MutableLiveData<ArrayList<CityBusesData>>()
    val showRouteImageDialog = MutableLiveData<String>()

    fun onCatchCityName(cityName: String?) {
        cityName?.let { city->
            startToSearchCityBusData(city)
        }
    }

    private fun startToSearchCityBusData(city: String) {
        mCompositeSubscription.add(ApiWrapper.getCityBusesData(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Tool.logLongMessage("Michael",it.toString())
                handleError()
            }
            .subscribe({ cityBusesData->
                cityBusesData?.let {
                    handleAllCityBusesData(it)
                }
            },{
                Tool.logLongMessage("Michael",it.toString())
                handleError()
            }))
    }

    private fun handleAllCityBusesData(cityBusesDataList: ArrayList<CityBusesData>) {
        Tool.logLongMessage("Michael","收到資料 : "+Gson().toJson(cityBusesDataList))

        busesDataListLiveData.value= cityBusesDataList

    }

    fun onRouteImageClick(url: String) {
        showRouteImageDialog.value = url
    }

}