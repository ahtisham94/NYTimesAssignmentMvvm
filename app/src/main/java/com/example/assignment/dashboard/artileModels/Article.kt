package com.example.assignment.dashboard.artileModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Long = 0L,

    @SerializedName("uri")
    var uri: String = "",

    @SerializedName("url")
    var url: String = "",

    @SerializedName("published_date")
    var publishedDate: String = "",

    @SerializedName("updated")
    var updated: String = "",

    @SerializedName("section")
    var section: String = "",

    @SerializedName("type")
    var type: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("abstract")
    var abstractArticle: String = "",

    @SerializedName("byline")
    var byline: String = "",
) : Parcelable