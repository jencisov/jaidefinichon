package com.kuma.jaidefinichon.data.database

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import com.kuma.jaidefinichon.data.database.entity.PostEntity
import com.kuma.jaidefinichon.data.webservice.JaidefinichonServiceImpl
import java.util.concurrent.atomic.AtomicBoolean

object DatabaseCreator {

    private const val DATABASE_NAME = "JaidefinichonBD"

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()
    private val mInitializing = AtomicBoolean(true)

    private lateinit var mDb: AppDatabase

    fun createDb(context: Context) {
        if (!mInitializing.compareAndSet(true, false))
            return

        mIsDatabaseCreated.value = false
        CreateDbAsyncTaskData().execute(context)
    }

    fun isDatabaseCreated() = mIsDatabaseCreated

    fun getDatabase() = mDb

    fun loadPosts(context: Context, page: Int) {
        mIsDatabaseCreated.value = false
        LoadPostsAsyncTaskData(page).execute(context)
    }

    private class CreateDbAsyncTaskData() : DataBaseAsyncTask() {

        override fun doInBackground(vararg params: Context): Void? {
            val context = params[0].applicationContext

            context.deleteDatabase(DATABASE_NAME)

            val db = appDataBase(context)

            loadPosts(db, 0)

            mDb = appDataBase(context)

            return null
        }

        override fun onPostExecute(ignored: Void?) {
            mIsDatabaseCreated.value = true
        }

    }

    private class LoadPostsAsyncTaskData(val page: Int) : DataBaseAsyncTask() {

        override fun doInBackground(vararg params: Context): Void? {
            val context = params[0]
            val db = appDataBase(context)

            loadPosts(db, page)
            return null
        }

        override fun onPostExecute(result: Void?) {
            mIsDatabaseCreated.value = true
        }

    }

    private fun appDataBase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    private fun loadPosts(db: AppDatabase, pageNumber: Int) {
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

    private abstract class DataBaseAsyncTask : AsyncTask<Context, Void, Void>()

}