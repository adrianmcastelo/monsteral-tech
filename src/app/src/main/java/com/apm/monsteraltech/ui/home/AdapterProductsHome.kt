package com.apm.monsteraltech.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R

class AdapterProductsHome(private val productList: ArrayList<Product>): RecyclerView.Adapter<AdapterProductsHome.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textProductName: TextView = itemView.findViewById(R.id.product_name)
        private val imageProduct: ImageView = itemView.findViewById(R.id.product_image_imageview)
        private val textPrice: TextView = itemView.findViewById(R.id.product_price)

        fun setData(product: Product) {
            textProductName.text = product.productName
            //TODO: Cargar imagenes de los productos aquí
            // Si hicieramos "$${product.price}" tendríamos una vulnerabilidad de inyección de código
            textPrice.text = itemView.context.getString(R.string.product_price, product.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_products, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}