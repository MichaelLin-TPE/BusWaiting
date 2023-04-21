package com.bus.buswaiting.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bus.buswaiting.tool.Tool
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application){

    protected val mCompositeSubscription = CompositeDisposable()


    fun handleError(){
        Tool.showToast("網路不給力,請稍後再試")
    }

    open fun handleCoroutineException(throwable: Throwable) {
        // 處理協程異常的共享邏輯
    }
    fun clearCompositeDisposable(){
        mCompositeSubscription.dispose()
    }
}