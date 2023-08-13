package com.finalproject.newsquill.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.newsquill.BuildConfig.API_KEY
import com.finalproject.newsquill.adapter.TopHeadlinesAdapter
import com.finalproject.newsquill.api.ApiInstance
import com.finalproject.newsquill.databinding.FragmentHomeBinding
import com.finalproject.newsquill.topheadline_response.TopHeadlineArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var topHeadlinesAdapter: TopHeadlinesAdapter
    private var topHeadlineList: List<TopHeadlineArticle> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.topHeadline
        topHeadlinesAdapter = TopHeadlinesAdapter(topHeadlineList)
        recyclerView.adapter = topHeadlinesAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {

            val apiKey = API_KEY
            val lang = "en"
            val category = "general"

            try {
                val response: List<TopHeadlineArticle> = ApiInstance.retrofitService.getTopHeadline(apiKey, lang, category)

                // Update the adapter with the data
                requireActivity().runOnUiThread {
                    topHeadlinesAdapter.updateData(response)
                }
            } catch (e: IOException) {
                showToast("App error ${e.message}")
            } catch (e: HttpException) {
                val errorMessage = "Http error ${e.message}, code: ${e.code()}"
                showToast(errorMessage)
                e.response()?.errorBody()?.string()?.let {
                    Log.e("HttpError", it)
                }
            }
        }
    }

    private fun showToast(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}