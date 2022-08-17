package com.example.assignment.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.dashboard.adapters.ArticlesAdapter
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.extensions.loadImage

object DatabindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["loadImage"])
    fun loadImage(imageView: ImageView, url: Any = R.drawable.ic_articles_journal_svgrepo_com) {
        imageView.loadImage(url)
    }

    @JvmStatic
    @BindingAdapter(value = ["updateAdapter"])
    fun updateAdapter(recyclerView: RecyclerView, list: ArrayList<Article>) {
        if (recyclerView.adapter != null) {
            (recyclerView.adapter as ArticlesAdapter).setData(list.toMutableList())
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["filter"])
    fun filter(recyclerView: RecyclerView, filter: String) {
        if (recyclerView.adapter != null) {
            (recyclerView.adapter as ArticlesAdapter).filter(filter)
        }
    }
}