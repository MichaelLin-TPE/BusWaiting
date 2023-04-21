package com.bus.buswaiting.bean

import com.google.gson.annotations.SerializedName

data class RouteNameData(
    @SerializedName("PlateNumb")
    val plateNumb: String?,
    @SerializedName("OperatorID")
    val operatorId:String?,
    @SerializedName("OperatorNo")
    val operatorNo:String?,
    @SerializedName("RouteUID")
    val routeUID:String?,
    @SerializedName("RouteID")
    val routeID:String?,
    @SerializedName("RouteName")
    val routeName:RouteName?,
    @SerializedName("SubRouteUID")
    val subRouteUID:String?,
    @SerializedName("SubRouteID")
    val subRouteID:String?,
    @SerializedName("SubRouteName")
    val subRouteName:RouteName?,
    @SerializedName("Direction")
    val direction:Int,
    @SerializedName("BusPosition")
    val busPosition: BusPosition?,
    @SerializedName("speed")
    val speed:Double,
    @SerializedName("Azimuth")
    val azimuth:Double,
    @SerializedName("DutyStatus")
    val dutyStatus:Int,
    @SerializedName("BusStatus")
    val busStatus:Int,
    @SerializedName("MessageType")
    val messageType:Int,
    @SerializedName("GPSTime")
    val gpsTime:String?,
    @SerializedName("SrcRecTime")
    val srcRecTime:String?,
    @SerializedName("UpdateTime")
    val updateTime:String?

)

data class BusPosition(
    @SerializedName("PositionLon")
    val lon :Double,
    @SerializedName("PositionLat")
    val lat:Double,
    @SerializedName("GeoHash")
    val geoHash:String?
)

data class RouteName(
    @SerializedName("Zh_tw")
    val cnRouteName: String,
    @SerializedName("En")
    val enRouteName: String
)


