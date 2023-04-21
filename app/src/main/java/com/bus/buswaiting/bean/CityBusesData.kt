package com.bus.buswaiting.bean

import com.google.gson.annotations.SerializedName

data class CityBusesData(
    @SerializedName("RouteUID")
    val routeUID: String?,
    @SerializedName("RouteID")
    val routeID: String?,
    @SerializedName("AuthorityID")
    val authorityID: String?,
    @SerializedName("ProviderID")
    val providerID: String?,
    @SerializedName("BusRouteType")
    val busRouteType: Int,
    @SerializedName("RouteName")
    val routeName: CityBusesRouteName?,
    @SerializedName("DepartureStopNameZh")
    val departureStopNameZh: String?,
    @SerializedName("DepartureStopNameEn")
    val departureStopNameEn: String?,
    @SerializedName("DestinationStopNameZh")
    val destinationStopNameZh: String?,
    @SerializedName("DestinationStopNameEn")
    val destinationStopNameEn: String?,
    @SerializedName("RouteMapImageUrl")
    val routeMapImageUrl: String?,
    @SerializedName("City")
    val city: String?,
    @SerializedName("CityCode")
    val cityCode: String?,
    @SerializedName("UpdateTime")
    val updateTime: String?,
    @SerializedName("VersionID")
    val versionID: String?,
    @SerializedName("Operators")
    val operatorList: ArrayList<Operators>?,
    @SerializedName("SubRoutes")
    val subRoutes: ArrayList<CitySubRoutes>?,
    val isAddFav: Boolean
)

data class CityBusesRouteName(
    @SerializedName("Zh_tw")
    val cnRouteName: String,
    @SerializedName("En")
    val enRouteName: String
)

data class CitySubRoutes(
    @SerializedName("SubRouteUID")
    val subRouteUID: String,
    @SerializedName("SubRouteID")
    val subRouteID: String,
    @SerializedName("Headsign")
    val headSign: String
)

data class Operators(
    @SerializedName("OperatorID")
    val operatorID: String?,
    @SerializedName("OperatorCode")
    val OperatorCode: String?,
    @SerializedName("OperatorNo")
    val OperatorNo: String?,
    @SerializedName("OperatorName")
    val operatorName: OperatorName?
)

data class OperatorName(
    @SerializedName("Zh_tw")
    val operatorName: String
)