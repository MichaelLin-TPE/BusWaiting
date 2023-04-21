package com.bus.buswaiting.tool

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.bus.buswaiting.R

object GlideTool {

    private fun getRequestManger():RequestManager{
        return Glide.with(Tool.getContext())
    }

    fun showImage(url:String,imageView:ImageView){

        val fitXyOptions = RequestOptions()
            .transform(object : FitCenter(){
                override fun transform(
                    pool: BitmapPool,
                    toTransform: Bitmap,
                    outWidth: Int,
                    outHeight: Int
                ): Bitmap {
                    return Bitmap.createScaledBitmap(toTransform, outWidth, outHeight, true)
                }
            })

        getRequestManger()
            .load(url)
            .apply(fitXyOptions)
            .error(R.mipmap.app_logo_round)
            .into(imageView)
    }

}