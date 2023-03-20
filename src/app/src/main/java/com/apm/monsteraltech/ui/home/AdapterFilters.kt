package com.apm.monsteraltech.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R
import com.apm.monsteraltech.ui.home.AdapterFilters.*

class AdapterFilters(val filterList: ArrayList<Filter>): RecyclerView.Adapter<ViewHolder>() {

    private lateinit var listener: onItemClickedListener

    interface onItemClickedListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickedListener){
        this.listener = listener
    }


    class ViewHolder(itemView: View, listener: onItemClickedListener) : RecyclerView.ViewHolder(itemView) {
        private val btnFilter: Button = itemView.findViewById(R.id.button_filter)

        fun setData(filter: Filter) {
            btnFilter.text = filter.filterName
        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // cargamos el layout que va a tener el recycle
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_filter, parent, false)
        return ViewHolder(view,this.listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(filterList[position])
    }

    override fun getItemCount(): Int {
        return filterList.size
    }
}