package com.bus.buswaiting.bean

import com.google.gson.annotations.SerializedName

data class ETAData(
    @SerializedName("PlateNumb")
    val plateNumb:String?,
    @SerializedName("StopUID")
    val stopUID:String?,
    @SerializedName("StopID")
    val stopID:String?,
    @SerializedName("StopName")
    val stopName:RouteName?,
    @SerializedName("RouteUID")
    val routeUID:String?,
    @SerializedName("RouteID")
    val routeID:String?,
    @SerializedName("SubRouteUID")
    val subRouteUID:String?,
    @SerializedName("SubRouteID")
    val subRouteID:String?,
    @SerializedName("SubRouteName")
    val subRouteName:RouteName?,
    @SerializedName("Direction")
    val direction:Int,
    @SerializedName("StopCountDown")
    val stopCountDown:Int,
    @SerializedName("CurrentStop")
    val currentSop:String?,
    @SerializedName("StopSequence")
    val stopSequence:Int,
    @SerializedName("StopStatus")
    val stopStatus:Int,
    @SerializedName("MessageType")
    val messageType:Int,
    @SerializedName("IsLastBus")
    val isLastBus:Boolean,
    @SerializedName("DataTime")
    val dataTime:String?,
    @SerializedName("SrcTransTime")
    val srcTransTime:String?,
    @SerializedName("UpdateTime")
    val updateTime:String?
)
