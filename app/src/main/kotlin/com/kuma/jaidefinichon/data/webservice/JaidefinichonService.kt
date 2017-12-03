package com.kuma.jaidefinichon.data.webservice

import com.kuma.jaidefinichon.data.database.entity.PostEntity

interface JaidefinichonService {

    fun getAllPosts(pageNumber: Int): ArrayList<PostEntity>

}