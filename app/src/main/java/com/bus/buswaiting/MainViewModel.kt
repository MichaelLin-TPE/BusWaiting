package com.bus.buswaiting

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bus.buswaiting.application.MyApplication
import com.bus.buswaiting.base.BaseViewModel
import com.bus.buswaiting.bean.CityData
import com.bus.buswaiting.bean.ETAData
import com.bus.buswaiting.http.ApiWrapper
import com.bus.buswaiting.tool.Tool
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseViewModel(application) {

    val etaLiveData = MutableLiveData<ArrayList<ETAData>>()
    val cityListLiveData = MutableLiveData<ArrayList<CityData>>()
    val goToRouteNameActivityLiveData = MutableLiveData<CityData>()
    init {
        mCompositeSubscription.add(
            ApiWrapper.getVerifyToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.i("Michael", "error : $it")
                }
                .subscribe({
                    it?.accessToken?.let { token->
                        Tool.saveToken(token)
                        showCityList()
                    }
                }, {
                    Log.i("Michael", "error : $it")
                })
        )
    }

    private fun showCityList() {
        cityListLiveData.value = getCityData()
    }


    private fun getCityData():ArrayList<CityData>{
        val dataList = ArrayList<CityData>()
        dataList.add(CityData(getApplication<MyApplication>().getString(R.string.taipei),"Taipei",R.drawable.taipei_bg))
        dataList.add(CityData(getApplication<MyApplication>().getString(R.string.keelung),"Keelung",R.drawable.keelung_bg))
        dataList.add(CityData(getApplication<MyApplication>().getString(R.string.new_taipei),"NewTaipei",R.drawable.new_taipei_bg))
        return dataList
    }


    private fun checkETATime() {
        Tool.getToken()?.let {
            mCompositeSubscription.add(ApiWrapper.getEstimatedTimeOfArrival(
                "Keelung",
                "201",
                "Bearer $it"
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.i("Michael", "error : $it")
                }
                .subscribe({ res ->
                    Log.i(
                        "Michael",
                        "getEstimatedTimeOfArrival response : ${Gson().toJson(res)}"
                    )
                    etaLiveData.value = res

                }, {
                    Log.i("Michael", "error : $it")
                })
            )
        }

    }

    fun onDestroy() {
        clearCompositeDisposable()
    }

    fun setOnCityClick(data: CityData) {
        goToRouteNameActivityLiveData.value = data
    }

}