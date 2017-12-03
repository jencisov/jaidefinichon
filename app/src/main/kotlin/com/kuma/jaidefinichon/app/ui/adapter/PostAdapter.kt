package com.kuma.jaidefinichon.app.ui.adapter

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.kuma.jaidefinichon.app.model.Post
import com.kuma.jaidefinichon.databinding.PostItemBinding
import com.kuma.jaidefinichon.extensions.loadImageWith
import inflateBindingLayout

class PostAdapter() : Adapter() {
    var mPostsList: List<Post> = ArrayList()

    override fun setList(list: Collection<*>) {
        if (list.isNotEmpty()) {
            mPostsList = list as List<Post>

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        mPostsList.get(oldItemPosition).getId() == list.get(newItemPosition).getId()

                override fun getOldListSize() = mPostsList.size

                override fun getNewListSize() = list.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        list.get(newItemPosition) != list.get(oldItemPosition)

            })

            mPostsList = list.toMutableList();

            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflateBindingLayout(this) as PostItemBinding)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindPost(holder, position)
    }

    override fun getItemCount() = mPostsList.size

    private fun bindPost(holder: ViewHolder, position: Int) {
        with(holder.binding as PostItemBinding) {
            post = mPostsList.get(position)

            postItemImage.loadImageWith(post.getImageUrl())

            executePendingBindings()
        }
    }

}