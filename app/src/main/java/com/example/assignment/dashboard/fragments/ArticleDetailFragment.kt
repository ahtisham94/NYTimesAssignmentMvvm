package com.example.assignment.dashboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.assignment.BR
import com.example.assignment.R
import com.example.assignment.coreBase.BaseFragment
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import com.example.assignment.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding, DashboardViewmodel>() {
    private val viewmodel: DashboardViewmodel by activityViewModels()

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun getViewModels() = viewmodel

    override fun getBindingVariable() = BR.articleDetail

    override fun getLayoutId() = R.layout.fragment_article_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewmodel.articleDetailsObserver.articleTile = args.articles?.title
        viewmodel.articleDetailsObserver.articleAbstract = args.articles?.abstractArticle
        viewmodel.articleDetailsObserver.articlePublishedDate =
            String.format(getString(R.string.published_date, args.articles?.publishedDate))
    }
}