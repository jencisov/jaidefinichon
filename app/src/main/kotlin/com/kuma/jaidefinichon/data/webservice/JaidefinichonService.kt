package com.kuma.jaidefinichon.data.webservice

import com.kuma.jaidefinichon.data.database.entity.PostEntity

/**
 * Created by Jorge.Enciso on 25/07/2017.
 */
interface JaidefinichonService {

  fun getAllPosts(pageNumber: Int): ArrayList<PostEntity>

}