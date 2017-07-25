package com.kuma.jaidefinichon.app.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.kuma.jaidefinichon.R
import com.kuma.jaidefinichon.app.ui.viewmodel.MainActivityVM
import com.kuma.jaidefinichon.data.database.DatabaseCreator
import com.kuma.jaidefinichon.data.database.entity.PostEntity
import com.kuma.jaidefinichon.databinding.ActivityMainBinding
import inflateActivityBindingLayout

class MainActivity : BaseActivity() {
  private val mBinding by lazy {
    inflateActivityBindingLayout(R.layout.activity_main) as ActivityMainBinding
  }
  private val mViewModel by lazy {
    ViewModelProviders.of(this).get(MainActivityVM::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DatabaseCreator.createDb(this.application)
    mBinding.root

    subscribeUi()
  }

  fun subscribeUi() {
    mViewModel.mObservablePosts.observe(this, Observer<List<PostEntity>> {
      myPosts ->
      if (myPosts != null) {
        
      }
    })
  }

}