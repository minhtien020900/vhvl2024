package com.example.quanlynguoidung

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class UserAdapter(
    private val list: ArrayList<User>,
    private val onEditClick: (User) -> Unit,
    private val onDeleteClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)
        val txtPhone: TextView = itemView.findViewById(R.id.txtPhone)
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.txtName.text = user.name
        holder.txtEmail.text = user.email
        holder.txtPhone.text = user.phone

        // Hiển thị avatar
        if (!user.avatarPath.isNullOrEmpty()) {
            val file = File(user.avatarPath!!)
            if (file.exists()) {
                holder.imgAvatar.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
            } else {
                holder.imgAvatar.setImageResource(R.drawable.ic_launcher_foreground)
            }
        } else {
            holder.imgAvatar.setImageResource(R.drawable.ic_launcher_background)
        }

        // Nút sửa
        holder.btnEdit.setOnClickListener { onEditClick(user) }

        // Nút xóa
        holder.btnDelete.setOnClickListener { onDeleteClick(user) }
    }
}