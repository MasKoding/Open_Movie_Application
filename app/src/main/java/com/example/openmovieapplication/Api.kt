package com.example.openmovieapplication


import com.example.openmovieapplication.modeldata.MovieDetailData
import com.example.openmovieapplication.modeldata.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    fun getMovies(
        @Query("s") s:String?,
        @Query("apikey") apikey:String
    ): Call<SearchData>

    @GET("/")
    fun getDetailMovie(
        @Query("i") i:String?,
        @Query("apikey") apikey:String,
    ): Call<MovieDetailData>
}