package com.example.assignment.dashboard

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import com.example.assignment.R
import com.example.assignment.coreBase.BaseActivity
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import com.example.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityMainBinding, DashboardViewmodel>() {

    val dashboardViewmodel: DashboardViewmodel by viewModels()

    override fun getViewModels() = dashboardViewmodel

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding?.toolbar?.toolbar)
        getViewModels().getArticles()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val search = menu?.findItem(R.id.appSearchBar)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                getViewModels().articleObserver.filter = newText.toString()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}
