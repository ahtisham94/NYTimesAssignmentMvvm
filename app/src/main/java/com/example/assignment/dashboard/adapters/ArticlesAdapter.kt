package com.example.assignment.dashboard.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.coreBase.FilterCallback
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.dashboard.dashboardObservers.MyArticleDetailObserver
import com.example.assignment.databinding.ItemArticlesLayoutBinding
import com.example.assignment.extensions.setOnSingleClickListener


class ArticlesAdapter(val block: (res: Any) -> Unit) :
    ListAdapter<Article, ArticlesAdapter.MyArticlesHolder>(DiffCallback()), FilterCallback {
    private var list = mutableListOf<Article>()


    inner class MyArticlesHolder(val binding: ItemArticlesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            val observers = MyArticleDetailObserver()
            observers.articleTile = item.title
            observers.articleAbstract = item.byline
            observers.articlePublishedDate = String.format(
                binding.root.context.getString(R.string.published_date),
                item.publishedDate
            )
            binding.rootLayout.setOnSingleClickListener {
                block.invoke(item)
            }
            binding.articleItem = observers
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArticlesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticlesLayoutBinding.inflate(inflater, parent, false)
        return MyArticlesHolder(binding)
    }

    override fun onBindViewHolder(holder: MyArticlesHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    override fun filter(filter: String) {
        if (filter.isNotEmpty()) {
            val tempList = list.filter {
                it.title.contains(filter, true) ||
                        it.byline.contains(filter, true)
            }
            submitList(tempList)
        } else submitList(list)
    }

    fun setData(list: MutableList<Article>) {
        this.list = list
        submitList(list)
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem == newItem
}