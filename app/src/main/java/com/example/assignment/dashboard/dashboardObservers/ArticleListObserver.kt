package com.example.assignment.dashboard.dashboardObservers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.assignment.dashboard.artileModels.Article

class ArticleListObserver : BaseObservable() {

    var articleList: ArrayList<Article>? = arrayListOf()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.articleList)
        }

    var filter: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.filter)
        }

}