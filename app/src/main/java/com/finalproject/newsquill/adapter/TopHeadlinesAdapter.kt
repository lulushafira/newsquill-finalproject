package com.finalproject.newsquill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.newsquill.databinding.TopHeadlineItemBinding
import com.finalproject.newsquill.topheadline_response.TopHeadlineArticle


class TopHeadlinesAdapter(


    private var topHeadlineList: List<TopHeadlineArticle> = emptyList()
    ) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {

    fun updateData(newList: List<TopHeadlineArticle>) {
        topHeadlineList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TopHeadlineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.headlineTitle
        val desc = binding.headlineDesc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TopHeadlineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return topHeadlineList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = topHeadlineList[position]

        holder.title.text = list.title
        holder.desc.text = list.description
    }

}
