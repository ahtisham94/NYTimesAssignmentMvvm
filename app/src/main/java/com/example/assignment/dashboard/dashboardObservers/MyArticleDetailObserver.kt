package com.example.assignment.dashboard.dashboardObservers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.assignment.R

class MyArticleDetailObserver : BaseObservable() {


    var articleTile: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.articleTile)
        }

    var articleAbstract: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.articleAbstract)
        }

    var articlePublishedDate: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.articlePublishedDate)
        }

    var articleUrl: Int = R.drawable.ic_articles_journal_svgrepo_com
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.articleUrl)
        }

}