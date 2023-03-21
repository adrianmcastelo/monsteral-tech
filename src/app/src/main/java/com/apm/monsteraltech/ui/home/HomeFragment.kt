package com.apm.monsteraltech.ui.home

import AdapterFilters
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private lateinit var filterRecyclerView: RecyclerView
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productsList: ArrayList<Product?>
    private lateinit var adapterProduct: AdapterProductsHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        val adapterFilter = AdapterFilters(getFilterList())
        this.productsList = getProductList()

        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterName(newText)
                return false
            }
        })

        filterRecyclerView = view.findViewById(R.id.RecyclerViewFilters)
        filterRecyclerView.adapter = adapterFilter
        filterRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        this.adapterProduct = AdapterProductsHome(productsList)
        productRecyclerView = view.findViewById(R.id.RecyclerViewProducts)
        productRecyclerView.adapter = this.adapterProduct
        productRecyclerView.layoutManager = layoutManager

        adapterFilter.setOnItemClickListener(object: AdapterFilters.OnItemClickedListener{
            override fun onItemClick(position: Int) {
                val button = adapterFilter.getButton(position)
                button.isSelected = !button.isSelected
                filterCategory(adapterFilter.getFilter(position).filterName)
            }
        })

        adapterProduct.setOnItemClickListener(object: AdapterProductsHome.OnItemClickedListener{
            override fun onItemClick(position: Int) {
                val selectedFilter = productRecyclerView.getChildAt(position)
                selectedFilter.isSelected = true
                Toast.makeText(requireContext(),adapterProduct.getProduct(position)?.category,Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    private fun filterCategory(text: String) {
        val filteredlist = ArrayList<Product?>()
        for (item in productsList) {
            if (item != null) {
                if (item.category.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapterProduct.filterList(filteredlist)
        }
    }

    private fun filterName(text: String) {
        val filteredlist = ArrayList<Product?>()
        for (item in productsList) {
            if (item != null) {
                if (item.productName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapterProduct.filterList(filteredlist)
        }
    }
    private fun getFilterList(): ArrayList<Filter> {
        val filterList: ArrayList<Filter> = arrayListOf()

        filterList.add(Filter("Choches"))
        filterList.add(Filter("Casas"))
        filterList.add(Filter("Electrodomésticos"))
        filterList.add(Filter("Muebles"))
        return filterList
    }

    private fun getProductList(): ArrayList<Product?> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo
        val productList: ArrayList<Product?> = arrayListOf()
        val category = ArrayList<String>()
        category.add("Coches")
        category.add("Casas")
        category.add("Electrodomésticos")
        category.add("Muebles")
        val productNames = arrayListOf(
            "Auriculares inalámbricos",
            "Reloj inteligente",
            "Sofá cama",
            "Camiseta de algodón",
            "Bicicleta de montaña",
            "Herramientas de jardinería",
            "Juego de sábanas de seda",
            "Cafetera de goteo",
            "Barbacoa de carbón",
            "Zapatillas deportivas"
        )
// Agrega algunos productos a la lista para mockear la respuesta
        for (i in 0 until 10) {
            val productName = productNames[(0 until productNames.size).random()]
            var productPrice = (1..1000).random().toDouble()
            val product = Product(productName, "", "Owner", productPrice.toString(),
                category[(0 until category.size).random()]
            )
            productList.add(product)
        }

        return productList
    }
}