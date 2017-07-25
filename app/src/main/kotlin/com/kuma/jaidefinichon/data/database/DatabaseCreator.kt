package com.kuma.jaidefinichon.data.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Jorge.Enciso on 25/07/2017.
 */
object DatabaseCreator {
  private const val dataBaseName = "JaidefinichonBD"
  private val mIsDatabaseCreated = MutableLiveData<Boolean>()
  private lateinit var mDb: AppDatabase
  private val mInitializing = AtomicBoolean(true)

  fun isDatabaseCreated(): LiveData<Boolean> = mIsDatabaseCreated
  fun getDatabase(): AppDatabase = mDb

  fun createDb(context: Context) {
    if (!mInitializing.compareAndSet(true, false))
      return

    mIsDatabaseCreated.value = false
    CreateDbAsyncTaskData().execute(context)
  }

  fun loadPosts(context: Context, page: Int, customLink: String) {
    mIsDatabaseCreated.value = false
    LoadPostsAsyncTaskData(page).execute(context)
  }

  private class CreateDbAsyncTaskData() : DataBaseAsyncTask() {

    override fun doInBackground(vararg params: Context): Void? {
      val context = params[0].applicationContext
      context.deleteDatabase(dataBaseName)
      val db = appDataBase(context)
      DatabaseInitUtil.loadPosts(db, 0)
      mDb = db
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
      DatabaseInitUtil.loadPosts(db, page)
      mDb = db
      return null
    }

    override fun onPostExecute(result: Void?) {
      mIsDatabaseCreated.value = true
    }

  }

  private abstract class DataBaseAsyncTask : AsyncTask<Context, Void, Void>()

  private fun appDataBase(context: Context) =
      Room.databaseBuilder(context, AppDatabase::class.java, dataBaseName).build()

}