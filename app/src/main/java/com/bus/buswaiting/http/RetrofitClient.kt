package com.bus.buswaiting.http

import android.os.Build
import androidx.annotation.RequiresApi
import com.bus.buswaiting.tool.Tool
import com.hele.hele_news.http.retrofit.CoroutineCallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale


object RetrofitClient {

    private var retrofit : Retrofit? = null


    fun getClient() : Retrofit{
        if (retrofit == null){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            retrofit = Retrofit.Builder()
                .baseUrl(Tool.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build()
            return retrofit as Retrofit
        }
        return retrofit as Retrofit
    }


}