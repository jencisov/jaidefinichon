package com.kuma.jaidefinichon.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kuma.jaidefinichon.data.database.dao.PostDao
import com.kuma.jaidefinichon.data.database.entity.PostEntity

@Database(entities = arrayOf(PostEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

}