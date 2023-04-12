package com.apm.monsteraltech.ui.home

import AdapterFilters
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R
import com.apm.monsteraltech.Searchable
import com.apm.monsteraltech.ui.home.filters.FiltersHomeActivity
import java.util.*


@Suppress("DEPRECATION")
class HomeFragment : Fragment(), Searchable {
    private lateinit var filterRecyclerView: RecyclerView
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productsList: ArrayList<Product?>
    private lateinit var adapterProduct: AdapterProductsHome

    private var context: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (savedInstanceState != null) {
            //La no deprecada requiere API 33
            productsList = savedInstanceState.getParcelableArrayList<Product>("productsList")!!
        }else{
            this.productsList = getProductList()
        }

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        val adapterFilter = AdapterFilters(getFilterList())


        //Inicializamos la vista de filtros
        filterRecyclerView = view.findViewById(R.id.RecyclerViewFilters )
        filterRecyclerView.adapter = adapterFilter
        filterRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //Inicializamos la vista de los productos y hacemos que se muestren en filas de 2
        val layoutManager = GridLayoutManager(requireContext(), 2)
        this.adapterProduct = AdapterProductsHome(productsList)
        productRecyclerView = view.findViewById(R.id.RecyclerViewProducts)
        productRecyclerView.adapter = this.adapterProduct
        productRecyclerView.layoutManager = layoutManager

        adapterFilter.setOnItemClickListener(object: AdapterFilters.OnItemClickedListener{
            override fun onItemClick(position: Int) {
/*                val button = adapterFilter.getButton(position)
                button.isSelected = !button.isSelected
                filterCategory(adapterFilter.getFilter(position).filterName)*/
                val sendIntent: Intent = Intent(requireContext(), FiltersHomeActivity::class.java).apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, adapterFilter.getFilter(position).filterName)
                    type = "text/plain"
                }
                startActivity(sendIntent)

            }
        })

        //LLamamos a la actividad producto detail
        adapterProduct.setOnItemClickListener(object: AdapterProductsHome.OnItemClickedListener{
            override fun onItemClick(position: Int) {
                val selectedFilter = productRecyclerView.getChildAt(position)
                selectedFilter.isSelected = true
                val intent = Intent(requireContext(), com.apm.monsteraltech.ProductDetail::class.java)
                //TODO: ver que información es necesario pasarle
                intent.putExtra("Product",adapterProduct.getProduct(position)?.productName);
                //TODO: ver si ponerle la flecha para volver atrás (la documentación no lo recomienda)
                startActivity(intent)
            }
        })
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("productList", productsList)
    }


    // filtramos por nombre de la categoría seleccionada
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

    private fun getFilterList(): ArrayList<Filter> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo

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

    // filtramos por nombre del producto destacado
    //TODO: Si hay una categoría seleccionada tendría que buscar solo sobre esos objetos
    override fun onSearch(text: String?) {
        // Aquí filtramos por los productos destacados pero pienso que habría que buscar sobre el total
        // de productos que podamos obtener

        val filteredlist = ArrayList<Product?>()
        for (item in productsList) {
            if (item != null) {
                if (text != null) {
                    if (item.productName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                        filteredlist.add(item)
                    }
                }
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapterProduct.filterList(filteredlist)
        }
    }


}