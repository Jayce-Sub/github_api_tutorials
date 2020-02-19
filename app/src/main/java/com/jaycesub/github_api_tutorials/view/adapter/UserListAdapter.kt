package com.jaycesub.github_api_tutorials.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.Items
import kotlinx.android.synthetic.main.item_userlist.view.*

class UserListAdapter(private var itemList: List<Items>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setItemList(itemList: List<Items>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: android.view.View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val item = itemList[position]
            view.textView_id.text = item.login
            Glide.with(view)
                .load(item.avatar_url)
                .into(view.imageView_avatar)
        }
    }
}