package com.kuma.jaidefinichon.app.model

interface Post {
    fun getId(): Long
    fun getImageUrl(): String
    fun getUrl(): String
    fun getDate(): String
    fun getSource(): String
}