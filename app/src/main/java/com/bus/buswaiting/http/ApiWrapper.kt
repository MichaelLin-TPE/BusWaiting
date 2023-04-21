package com.bus.buswaiting.http

import android.media.session.MediaSession.Token
import com.bus.buswaiting.bean.CityBusesData
import com.bus.buswaiting.bean.ETAData
import com.bus.buswaiting.bean.RouteNameData
import com.bus.buswaiting.bean.TokenResponse
import com.bus.buswaiting.tool.Tool
import io.reactivex.Observable
import java.net.URL


object ApiWrapper {

    private fun getApiService(): ApiService =
        RetrofitClient.getClient().create(ApiService::class.java)

    fun getEstimatedTimeOfArrival(
        city: String,
        routeName: String,
        token: String
    ): Observable<ArrayList<ETAData>> {
        return getApiService().getEstimatedTimeOfArrival(
            city,
            routeName,
            30,
            "JSON",
            token
        )
    }

    fun getRoutNameETA(routeName: String, token: String): Observable<ArrayList<RouteNameData>> {
        return getApiService().getRoutNameETA(routeName, 30, "JSON", token)
    }

    fun getVerifyToken(): Observable<TokenResponse?> {
        return getApiService().getToken(
            "client_credentials",
            URLConstant.CLIENT_ID,
            URLConstant.APP_KEY
        )
    }

    fun getCityBusesData(cityName:String):Observable<ArrayList<CityBusesData>?>{
        val token = Tool.getToken()
        Tool.logLongMessage("Michael","token : $token")
        return if (token == null){
            getApiService().getCityBusesData(cityName,"JSON",
                "")
        }else{
            getApiService().getCityBusesData(cityName,"JSON",
                "Bearer $token")
        }
    }

}