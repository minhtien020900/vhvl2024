package com.example.a09_baitapquanlycv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: List<TaskModel>, private val activity: MainActivity) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        val imgProduct: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val desc: TextView = itemView.findViewById(R.id.desc)
        val status: TextView = itemView.findViewById(R.id.status)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.name.text = task.name
        holder.desc.text = "${task.desc}"
        if (task.status == 0) {
            holder.status.text = "Chưa hoàn thành"

        } else {
            holder.status.text = "Đã hoàn thành"

        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}