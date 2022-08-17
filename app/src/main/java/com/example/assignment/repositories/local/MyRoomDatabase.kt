package com.example.assignment.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.dashboard.artileModels.Article

@Database(entities = [Article::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun getMyDao(): MyDao
}