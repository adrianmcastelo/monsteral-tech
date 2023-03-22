package com.apm.monsteraltech.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R

class AdapterProductsHome(private var productList: ArrayList<Product?>): RecyclerView.Adapter<AdapterProductsHome.ViewHolder>() {
    private lateinit var listener: OnItemClickedListener

    interface OnItemClickedListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickedListener){
        this.listener = listener
    }
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

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<Product?>) {
        /*
        below line is to add our filtered
        list in our course array list.
        */
        productList = filterlist
        /*
        below line is to notify our adapter
        as change in recycler view data.
        */
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productList[position]?.let { holder.setData(it) }

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun getProduct(position: Int): Product? {
        return productList[position]
    }
}