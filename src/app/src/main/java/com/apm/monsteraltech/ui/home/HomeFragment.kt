package com.apm.monsteraltech.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R




class HomeFragment : Fragment() {
    private lateinit var filterRecycleView: RecyclerView
    private lateinit var productRecycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_home, container, false)

        // Necesitamos configurar un Layout al Recycler para que funcione
        var adapter = AdapterFilters(getFilterList())

        filterRecycleView = view.findViewById(R.id.RecyclerViewFilters)
        filterRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        filterRecycleView.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(), 2)

        productRecycleView = view.findViewById(R.id.RecyclerViewProducts)
        productRecycleView.adapter = AdapterProductsHome(getProductList())
        productRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        productRecycleView.layoutManager = layoutManager

        adapter.setOnItemClickListener(object: AdapterFilters.onItemClickedListener{
            override fun onItemClick(position: Int) {
                val selectedFilter = filterRecycleView.getChildAt(position)
                selectedFilter.isSelected = true
            }
        })

        return view
    }

    private fun getFilterList(): ArrayList<Filter> {
        val filterList: ArrayList<Filter> = arrayListOf()

        filterList.add(Filter("Choches"))
        filterList.add(Filter("Casas"))
        filterList.add(Filter("Electrodomésticos"))
        filterList.add(Filter("Muebles"))
        return filterList
    }

    private fun getProductList(): ArrayList<Product> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo
        val productList: ArrayList<Product> = arrayListOf()

        // Agrega algunos productos a la lista para mockear la respuesta
        for (i in 0 until 10) {
            val product = Product("Producto $i","", "Owner", "99.99")
            productList.add(product)
        }

        return productList
    }
}