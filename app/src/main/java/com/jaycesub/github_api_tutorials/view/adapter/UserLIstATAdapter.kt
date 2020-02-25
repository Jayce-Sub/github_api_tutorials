package com.jaycesub.github_api_tutorials.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.Items
import java.util.*
import kotlin.collections.ArrayList

class UserLIstATAdapter(
    context: Context,
    @LayoutRes val resourceId: Int,
    private var items: ArrayList<Items>
) : ArrayAdapter<Items>(context, resourceId, items) {

    private var itemFilter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            val suggestions = ArrayList<Items>()
            if(constraint == null || constraint.isEmpty()) {
                suggestions.addAll(items)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()

                for(item in items) {
                    if(item.login.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        suggestions.add(item)
                    } // else
                }
            }

            results.values = suggestions
            results.count = suggestions.size

            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            clear()
            addAll(results!!.values as List<Items>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Items).login
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if(convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            view = convertView
        }

        viewHolder.bind(position)

        return view
    }


    override fun getItem(position: Int): Items? {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    fun setItems(items: ArrayList<Items>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ViewHolder(private val view: View) {

        private val imageView = view.findViewById<ImageView>(R.id.imageView_thumbnail)
        private val textView = view.findViewById<TextView>(R.id.textView_preview)

        fun bind(position: Int) {
            val item = items[position]
            Glide.with(view)
                .load(item.avatar_url)
                .into(imageView)

            textView.text = item.login
        }
    }
}