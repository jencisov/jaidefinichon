package com.kuma.jaidefinichon.data.webservice

import com.kuma.jaidefinichon.data.database.entity.PostEntity
import com.kuma.jaidefinichon.extensions.toLongHash
import org.jsoup.Jsoup

class JaidefinichonServiceImpl : JaidefinichonService {
    private val baseUrl = "http://v2.jaidefinichon.com/mobile"

    override fun getAllPosts(pageNumber: Int): ArrayList<PostEntity> {
        val postsList = arrayListOf<PostEntity>()

        try {
            val url = baseUrl + "/page/$pageNumber"
            val document = Jsoup.connect(url).get()
            val links = document.select("p")
            val date = document.select("h2")
            var post: PostEntity

            for ((index, link) in links.withIndex()) {
                val imageUrl = link.select("a").attr("href")

                if (imageUrl.isNotEmpty() and
                        imageUrl.contains(".jpg") or
                        imageUrl.contains(".png") or
                        imageUrl.contains(".gif")) {
                    post = PostEntity()

                    post.setId(imageUrl.toLongHash())
                    post.setImageUrl(imageUrl)
                    post.setDate(date.text())
                    post.setSource(links[index + 1].text())

                    postsList.add(post)
                }
            }

            return postsList
        } catch (e: Exception) {
            return postsList
        }

    }

}