package com.kuma.jaidefinichon.app.model

import java.io.Serializable

/**
 * Created by Jorge.Enciso on 25/07/2017.
 */
interface Post {
  fun getId(): Long
  fun getImageUrl(): String
  fun getUrl(): String
  fun getDate(): String
  fun getTitle(): String
}