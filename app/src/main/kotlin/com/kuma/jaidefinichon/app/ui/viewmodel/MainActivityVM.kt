package com.kuma.jaidefinichon.app.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.kuma.jaidefinichon.data.database.DatabaseCreator
import com.kuma.jaidefinichon.data.database.entity.PostEntity

class MainActivityVM(app: Application) : AndroidViewModel(app) {

    val mObservablePosts: LiveData<List<PostEntity>>

    init {
        val databaseCreated = DatabaseCreator.isDatabaseCreated()

        initAbsentValues()

        mObservablePosts = Transformations.switchMap<Boolean, List<PostEntity>>(databaseCreated) {
            isDbCreated ->
            if (!isDbCreated)
                ABSENT_POST
            else
                DatabaseCreator.getDatabase().postDao().loadAllPosts()
        }
    }

    private fun initAbsentValues() {
        ABSENT_POST.value = null
    }

    companion object {
        private val ABSENT_POST: MutableLiveData<List<PostEntity>> = MutableLiveData()
    }

}