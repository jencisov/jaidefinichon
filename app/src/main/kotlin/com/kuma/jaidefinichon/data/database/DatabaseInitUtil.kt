package com.kuma.jaidefinichon.data.database

import com.kuma.jaidefinichon.data.database.entity.PostEntity
import com.kuma.jaidefinichon.data.webservice.JaidefinichonServiceImpl

/**
 * Created by Jorge.Enciso on 25/07/2017.
 */
object DatabaseInitUtil {

  fun loadPosts(db: AppDatabase, pageNumber: Int) {
    val posts = JaidefinichonServiceImpl().getAllPosts(pageNumber)
    insertData(db, posts)
  }

  private fun insertData(db: AppDatabase, posts: List<PostEntity>) {
    db.beginTransaction()

    try {
      db.postDao().insertAll(posts)
      db.setTransactionSuccessful()
    } finally {
      db.endTransaction()
    }
  }

}