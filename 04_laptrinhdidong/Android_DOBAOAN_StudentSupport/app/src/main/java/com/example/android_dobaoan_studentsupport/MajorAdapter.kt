package com.example.android_dobaoan_studentsupport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MajorAdapter(
    private var majors: List<MajorInfo>,
    private val onFavoriteClick: (MajorInfo) -> Unit
) : RecyclerView.Adapter<MajorAdapter.MajorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_major, parent, false)
        return MajorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MajorViewHolder, position: Int) {
        holder.bind(majors[position])
    }

    override fun getItemCount() = majors.size

    fun updateData(newMajors: List<MajorInfo>) {
        this.majors = newMajors
        notifyDataSetChanged()
    }

    inner class MajorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMajorName: TextView = itemView.findViewById(R.id.tvMajorName)
        private val tvUniversityName: TextView = itemView.findViewById(R.id.tvUniversityName)
        private val tvBlock: TextView = itemView.findViewById(R.id.tvBlock)
        private val tvScore: TextView = itemView.findViewById(R.id.tvScore)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)

        fun bind(majorInfo: MajorInfo) {
            tvMajorName.text = majorInfo.majorName
            tvUniversityName.text = majorInfo.universityName
            tvBlock.text = "Khối: ${majorInfo.admissionBlock}"
            tvScore.text = majorInfo.benchmarkScore.toString()

            if (majorInfo.isFavorite) {
                btnFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                btnFavorite.setImageResource(R.drawable.ic_favorite_border)
            }

            btnFavorite.setOnClickListener {
                onFavoriteClick(majorInfo)
            }
        }
    }
}
