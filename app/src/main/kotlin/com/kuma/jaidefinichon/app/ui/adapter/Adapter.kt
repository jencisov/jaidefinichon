package com.kuma.jaidefinichon.app.ui.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    abstract fun setList(list: Collection<*>)

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}