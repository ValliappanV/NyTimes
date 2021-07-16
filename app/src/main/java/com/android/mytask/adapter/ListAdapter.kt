package com.android.mytask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mytask.R
import com.android.mytask.model.Result
import com.android.mytask.ui.MainActivity
import com.android.mytask.view_holder.ListViewHolder

class ListAdapter(
    val context: Context,
    val mainActivity: MainActivity,
    var newsArrayList: ArrayList<Result>
) : RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.text = newsArrayList[position].title
        holder.body.text = newsArrayList[position].abstract
        holder.auth.text = newsArrayList[position].byline
        holder.date.text = newsArrayList[position].published_date
    }
}
