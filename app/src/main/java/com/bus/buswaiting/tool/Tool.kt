package com.bus.buswaiting.tool

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bus.buswaiting.application.MyApplication

object Tool {

    private var accessToken: String? = null

    fun getBaseUrl(): String = "https://tdx.transportdata.tw"
    fun saveToken(accessToken: String) {
        this.accessToken = accessToken
    }

    fun getToken(): String? = accessToken

    fun getDrawable(resourceId: Int): Drawable {
        return ContextCompat.getDrawable(getContext(), resourceId)!!
    }

    fun getContext(): Context = MyApplication.instance!!.applicationContext


    fun logLongMessage(tag: String, message: String) {
        if (message.length > 4000) {
            Log.i(tag, message.substring(0, 4000))
            logLongMessage(tag, message.substring(4000))
        } else {
            Log.i(tag, message)
        }
    }

    fun Int.toDp(): Int {
        val resources = getContext().resources
        val displayMetrics = resources.displayMetrics
        return (this * (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun showToast(msg: String) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show()
    }
}