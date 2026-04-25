package com.example.a03_btvn_recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList:MutableList<Product>,
                     private val onDeleteClick: (Product) -> Unit
    ): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(),
    Filterable {
    private var filteredList: List<Product> = productList.toMutableList()
    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredList[position]
        holder.productImage.setImageResource(product.resImgId)
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.btnDelete.setOnClickListener {
            onDeleteClick(product)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filteredList = if (charSearch.isEmpty()) {
                    productList
                } else {
                    productList.filter {

                        it.name.lowercase().contains(charSearch.lowercase())
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Product>
                notifyDataSetChanged()
            }
        }
    }
    fun remove(product: Product) {
        productList.remove(product)
//        filteredList = productList.toMutableList()
        notifyDataSetChanged()
    }
}