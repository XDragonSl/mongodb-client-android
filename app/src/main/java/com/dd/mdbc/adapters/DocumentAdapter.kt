package com.dd.mdbc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dd.mdbc.R

class DocumentAdapter(
    private val collections: List<Any>,
    private val rowLayout: Int,
    val context: Context
) : RecyclerView.Adapter<DocumentAdapter.DBViewHolder>() {

    class DBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val document: TextView = itemView.findViewById(R.id.document)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return DBViewHolder(v)
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.document.text = beautify(collections[position].toString())
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    private fun beautify(json: String): String {
        return json.replace("[{\\[,]".toRegex(), "$0\\\n")
            .replace("[}\\]]".toRegex(), "\\\n$0")
    }
}
