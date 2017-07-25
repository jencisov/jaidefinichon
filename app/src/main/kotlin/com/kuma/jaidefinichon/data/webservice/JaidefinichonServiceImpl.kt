package com.kuma.jaidefinichon.data.webservice

import com.kuma.jaidefinichon.data.database.entity.PostEntity
import org.jsoup.Jsoup

/**
 * Created by Jorge.Enciso on 25/07/2017.
 */
class JaidefinichonServiceImpl : JaidefinichonService {
  private val baseUrl = "http://v2.jaidefinichon.com/mobile"

  override fun getAllPosts(pageNumber: Int): ArrayList<PostEntity> {
    val postsList = arrayListOf<PostEntity>()

    try {
      val url = baseUrl + "/page/$pageNumber"
      val document = Jsoup.connect(url).get()
      val links = document.select("p")
      var post: PostEntity

      for (value in links) {
        post = PostEntity()
        post.setImageUrl(value.select("a").attr("href"))
        postsList.add(post)
      }

      return postsList
    } catch (e: Exception) {
      return postsList
    }

  }

}