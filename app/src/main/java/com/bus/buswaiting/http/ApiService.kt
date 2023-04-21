package com.bus.buswaiting.http

import com.bus.buswaiting.bean.CityBusesData
import com.bus.buswaiting.bean.ETAData
import com.bus.buswaiting.bean.RouteNameData
import com.bus.buswaiting.bean.TokenResponse
import com.bus.buswaiting.tool.Tool
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/basic/v2/Bus/EstimatedTimeOfArrival/City/{City}/{RouteName}")
    fun getEstimatedTimeOfArrival(
        @Path("City") city: String,
        @Path("RouteName") routeName: String,
        @Query("top") top: Int,
        @Query("format") format: String,
        @Header("Authorization")token:String
    ): Observable<ArrayList<ETAData>>

    @GET("/api/basic/v2/Bus/RealTimeByFrequency/Streaming/InterCity/{RouteName}")
    fun getRoutNameETA(@Path("RouteName")routeName:String,
                       @Query("top") top: Int,
                       @Query("format") format: String,
                       @Header("Authorization") authHeader: String) :Observable<ArrayList<RouteNameData>>

    @FormUrlEncoded
    @POST("/auth/realms/TDXConnect/protocol/openid-connect/token")
    fun getToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Observable<TokenResponse?>

    @GET("/api/basic/v2/Bus/Route/City/{City}")
    fun getCityBusesData(@Path("City")cityName:String,@Query("format")format: String,@Header("Authorization") authHeader: String) :  Observable<ArrayList<CityBusesData>?>


}