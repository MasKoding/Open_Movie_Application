package com.example.openmovieapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openmovieapplication.MovieAdapter
import com.example.openmovieapplication.RClient
import com.example.openmovieapplication.databinding.FragmentDataBinding
import com.example.openmovieapplication.modeldata.MovieData
import com.example.openmovieapplication.modeldata.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataFragment : Fragment() {
    private var _binding: FragmentDataBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val list = ArrayList<MovieData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(context)

        val bundle = arguments
        val s = bundle?.getString("carimovie")
        val apikey = "369a0d13"

        binding.progressBar.visibility

        RClient.instances.getMovies(s, apikey).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
//                val responseCode = response.code()
                val cekRes = response.body()?.res

                if (cekRes == "True"){
                    response.body()?.let { list.addAll(it.data) }
                    val adapter = MovieAdapter(list,requireContext())
                    binding.rvData.adapter = adapter
                    binding.progressBar.isVisible = false
                } else {
                    Toast.makeText(context,"Movie not found",Toast.LENGTH_LONG).show()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}