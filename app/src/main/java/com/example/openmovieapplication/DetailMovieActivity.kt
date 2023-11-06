package com.example.openmovieapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.openmovieapplication.databinding.ActivityDetailMovieBinding
import com.example.openmovieapplication.databinding.ActivityMainBinding
import com.example.openmovieapplication.modeldata.MovieDetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    var b:Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val i = b?.getString("imdbid")
        val apikey = "369a0d13"

        RClient.instances.getDetailMovie(i,apikey).enqueue(object : Callback<MovieDetailData>{
            override fun onResponse(
                call: Call<MovieDetailData>,
                response: Response<MovieDetailData>
            ) {
                binding.tvTahun.text = response.body()?.Year
                binding.tvJudulmovie.text = response.body()?.Title
                binding.tvTglrilis.text = response.body()?.rilis

                Glide.with(this@DetailMovieActivity).load(response.body()?.gambar)
                    .into(binding.imgGambarposter)
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}