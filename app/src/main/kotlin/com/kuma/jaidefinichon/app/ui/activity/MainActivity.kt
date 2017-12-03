package com.kuma.jaidefinichon.app.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import com.kuma.jaidefinichon.R
import com.kuma.jaidefinichon.app.listener.EndlessRecyclerViewScrollListener
import com.kuma.jaidefinichon.app.ui.adapter.PostAdapter
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
    private val mPostAdapter by lazy {
        PostAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        subscribeUi()
    }

    private fun initView() {
        with(mBinding) {
            mainRecycler.adapter = mPostAdapter
            mainRecycler.addItemDecoration(DividerItemDecoration(applicationContext, OrientationHelper.VERTICAL))
            mainRecycler.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mainRecycler.layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    DatabaseCreator.loadPosts(baseContext, page + 1)
                }
            })
        }
    }

    fun subscribeUi() {
        mViewModel.mObservablePosts.observe(this, Observer<List<PostEntity>> { myPosts ->
            if (myPosts != null && myPosts.isNotEmpty()) {
                mPostAdapter.setList(myPosts)
            }
        })
    }

}