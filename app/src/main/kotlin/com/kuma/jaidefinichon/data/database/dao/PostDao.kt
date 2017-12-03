package com.kuma.jaidefinichon.data.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kuma.jaidefinichon.data.database.entity.PostEntity

@Dao interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id DESC") fun loadAllPosts(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertAll(posts: List<PostEntity>)

}