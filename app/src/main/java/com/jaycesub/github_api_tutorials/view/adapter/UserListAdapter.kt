package com.jaycesub.github_api_tutorials.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.Items
import kotlinx.android.synthetic.main.item_userlist.view.*

class UserListAdapter(private var itemList: List<Items>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

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

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        fun bind(position: Int) {
            val item = itemList[position]
            view.textView_id.text = item.login

            Glide.with(view)
                .load(item.avatar_url)
                .apply(getRequestOptions())
                .into(view.imageView_avatar)
        }

        private fun getRequestOptions(): RequestOptions {

            val circleProgressBar = CircularProgressDrawable(view.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

            return RequestOptions()
                .placeholder(circleProgressBar)
                .skipMemoryCache(true)
                .fitCenter()
        }

        override fun onClick(v: View?) {
            listener?.onClick(v, adapterPosition)

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(view: View?, position: Int)
    }
}