package com.kuma.jaidefinichon.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.kuma.jaidefinichon.app.model.Post

@Entity(tableName = "posts")
data class PostEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        private var _id: Long = -1,
        @ColumnInfo(name = "imageUrl")
        private var _imageUrl: String = "",
        @ColumnInfo(name = "url")
        private var _url: String = "",
        @ColumnInfo(name = "date")
        private var _date: String = "",
        @ColumnInfo(name = "source")
        private var _source: String = "") : Post {

    override fun getId() = _id

    override fun getImageUrl() = _imageUrl

    override fun getUrl() = _url

    override fun getDate() = _date

    override fun getSource() = _source

    fun setId(id: Long) {
        _id = id
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl = imageUrl
    }

    fun setUrl(url: String) {
        _url = url
    }

    fun setDate(date: String) {
        _date = date
    }

    fun setSource(source: String) {
        _source = source
    }

}