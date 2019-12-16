package com.dd.mdbc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dd.mdbc.R

class DbAdapter(
    private val dbs: List<Pair<String, Any?>>,
    private val rowLayout: Int,
    val context: Context
) : RecyclerView.Adapter<DbAdapter.DBViewHolder>() {

    class DBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return DBViewHolder(v)
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.title.text = dbs[position].first.toString()
    }

    override fun getItemCount(): Int {
        return dbs.size
    }

}