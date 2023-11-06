package com.example.openmovieapplication.modeldata

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("Search") val data:List<MovieData>,
    @SerializedName("Total Results") val totalData:Int,
    @SerializedName("Response") val res:String
)
