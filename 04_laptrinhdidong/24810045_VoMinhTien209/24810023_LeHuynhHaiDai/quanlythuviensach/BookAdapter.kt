package com.example.quanlythuviensach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlythuviensach.R

class BookAdapter(private val list: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtAuthor: TextView = itemView.findViewById(R.id.txtAuthor)
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = list[position]
        holder.txtTitle.text = book.title
        holder.txtAuthor.text = book.author
        holder.txtCategory.text = book.category
        holder.txtPrice.text = "Giá: ${book.price}"
    }
}