package com.bus.buswaiting.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bus.buswaiting.MainViewModel
import com.bus.buswaiting.view_model.ViewModelFactory

open class BaseActivity : AppCompatActivity() {

    private val viewModelFactory = ViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun <T : ViewModel> getViewModel(viewModelClass: Class<T>): T {
        return ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }

}