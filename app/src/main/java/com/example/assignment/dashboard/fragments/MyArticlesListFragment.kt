package com.example.assignment.dashboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.assignment.BR
import com.example.assignment.R
import com.example.assignment.coreBase.BaseFragment
import com.example.assignment.dashboard.adapters.ArticlesAdapter
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import com.example.assignment.databinding.FragmentMyArticlesListBinding
import com.example.assignment.extensions.safeNavigateFromNavController

class MyArticlesListFragment : BaseFragment<FragmentMyArticlesListBinding, DashboardViewmodel>() {
    val viewmodel: DashboardViewmodel by activityViewModels()
    override fun getViewModels() = viewmodel

    override fun getBindingVariable() = BR.myArticleList

    override fun getLayoutId() = R.layout.fragment_my_articles_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observers()
    }

    private fun observers() {
        viewmodel.getArticleList().observe(viewLifecycleOwner) {
            viewmodel.articleObserver.articleList = it as ArrayList<Article>?
        }
    }

    private fun initView() {
        binding.articleRv.adapter = ArticlesAdapter() {
            safeNavigateFromNavController(
                MyArticlesListFragmentDirections.toArticleDetailsFragAction(
                    it as Article
                )
            )
        }
    }
}