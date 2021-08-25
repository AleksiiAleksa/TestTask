package com.example.testtask

import android.widget.TextView

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_item_layout.view.*


class RecyclerAdapter(postItems: MutableList<Data>?) : RecyclerView.Adapter<BaseViewHolder>() {
    private var isLoaderVisible = false
    private val Items: MutableList<Data>? = postItems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(com.example.testtask.R.layout.custom_item_layout, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return Items?.size ?: 0
    }

    fun addItems(postItems: List<Data>) {
        Items?.addAll(postItems)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        Items?.add(Data())
        notifyItemInserted(Items!!.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = Items!!.size - 1
        val item: Data = getItem(position)
        if (item != null) {
            Items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        Items!!.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Data {
        return Items!![position]
    }

    inner class ViewHolder internal constructor(itemView: View?) : BaseViewHolder(itemView) {
        val id: TextView = itemView!!.ident
        val name: TextView = itemView!!.name
        val login: TextView = itemView!!.login
        val description: TextView = itemView!!.description
        override fun clear() {}
        override fun onBind(position: Int) {
            super.onBind(position)
            val item: Data = Items!![position]
            id.text = "ID: "+item.id.toString()
            name.text = "Name: "+item.name
            login.text = "Login: "+ item.owner?.login
            description.text = "Description: ${item.description ?: "no description"}"
            itemView.setOnClickListener { view : View ->
                view.findNavController().navigate(com.example.testtask.R.id.navFragment2, bundleOf("full_name" to item.full_name))
            }
        }

    }

}