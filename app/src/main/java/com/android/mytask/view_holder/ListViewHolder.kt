package com.android.mytask.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
val title=itemView.title
val body=itemView.body
val auth=itemView.auth
val date=itemView.date
}