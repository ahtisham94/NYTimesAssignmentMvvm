package com.example.assignment.di

import com.example.assignment.dashboard.dashboardRepository.DashboardRepository
import com.example.assignment.repositories.local.MyDao
import com.example.assignment.repositories.remote.GetDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object ActivityModules {

    @ActivityScoped
    @Provides
    fun dashboardRepo(dataService: GetDataService,myDao: MyDao) = DashboardRepository(dataService,myDao);




}