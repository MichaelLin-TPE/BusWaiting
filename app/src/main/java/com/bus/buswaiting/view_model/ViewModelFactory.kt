package com.bus.buswaiting.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bus.buswaiting.MainViewModel
import com.bus.buswaiting.application.MyApplication
import com.bus.buswaiting.ui.route.RouteNameViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MyApplication.instance?.let {
                return MainViewModel(it) as T
            }
        }
        if (modelClass.isAssignableFrom(RouteNameViewModel::class.java)){
            MyApplication.instance?.let {
                return RouteNameViewModel(it) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}