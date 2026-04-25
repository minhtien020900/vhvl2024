package com.example.baitap_tailop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter_DiemMonHoc(private val context: Context, private val list: ArrayList<DiemMonHoc>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Any = list[position]
    override fun getItemId(position: Int): Long = list[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.diemmonhoc_activity, parent, false)
        val img = view.findViewById<ImageView>(R.id.imgSV)
        val tvSV = view.findViewById<TextView>(R.id.tvSinhVien)
        val tvDiem = view.findViewById<TextView>(R.id.tvDiem)

        val item = list[position]
        img.setImageResource(item.hinhAnh)
        tvSV.text = "Sinh viên: ${item.sinhVien}"
        tvDiem.text = "Điểm: ${item.diem}"

        return view
    }
}
