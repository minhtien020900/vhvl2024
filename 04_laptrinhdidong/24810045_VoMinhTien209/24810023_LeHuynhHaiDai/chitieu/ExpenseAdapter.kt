package com.example.baitap_180925

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter(private var items: List<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.VH>() {

    private val dbFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val showDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val showTime = SimpleDateFormat("HH:mm", Locale.getDefault())

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.tvTitle.text = e.title
        holder.tvAmount.text = String.format(Locale.getDefault(), "%,.0f đ", e.amount)

        try {
            val d = dbFormat.parse(e.datetime)
            if (d != null) {
                holder.tvDate.text = showDate.format(d)
                holder.tvTime.text = showTime.format(d)
            } else {
                holder.tvDate.text = e.datetime
                holder.tvTime.text = ""
            }
        } catch (ex: Exception) {
            holder.tvDate.text = e.datetime
            holder.tvTime.text = ""
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Expense>) {
        items = newItems
        notifyDataSetChanged()
    }
}