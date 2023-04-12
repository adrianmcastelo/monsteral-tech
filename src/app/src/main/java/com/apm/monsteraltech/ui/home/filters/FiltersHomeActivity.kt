package com.apm.monsteraltech.ui.home.filters

import AdapterFilters
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.ActionBarActivity
import com.apm.monsteraltech.R
import com.apm.monsteraltech.ui.home.AdapterProductsHome
import com.apm.monsteraltech.ui.home.Filter
import com.apm.monsteraltech.ui.home.Product


class FiltersHomeActivity : ActionBarActivity() {
    private lateinit var filterRecyclerView: RecyclerView
    private lateinit var adapterProduct: AdapterProductsHome
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productsList: ArrayList<Product?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters_home)
        //Activa la flecha para atrás
        val adapterFilter = AdapterFilters(getFilterList())
        this.productsList = getProductList()


        var textView: TextView = findViewById(R.id.textSelectedFilter)
        textView.text = intent.getStringExtra(Intent.EXTRA_TEXT)

        //Inicializamos la vista de filtros
        filterRecyclerView = findViewById(R.id.RecyclerViewActivtyFilters )
        filterRecyclerView.adapter = adapterFilter
        filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val layoutManager = GridLayoutManager(this, 2)
        this.adapterProduct = AdapterProductsHome(productsList)
        productRecyclerView = findViewById(R.id.RecyclerViewProducts)
        productRecyclerView.adapter = this.adapterProduct
        productRecyclerView.layoutManager = layoutManager

        adapterFilter.setOnItemClickListener(object: AdapterFilters.OnItemClickedListener{
            override fun onItemClick(position: Int) {
/*                val button = adapterFilter.getButton(position)
                button.isSelected = !button.isSelected
                filterCategory(adapterFilter.getFilter(position).filterName)*/
                val sendIntent: Intent = Intent(this@FiltersHomeActivity, FiltersHomeActivity::class.java).apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, adapterFilter.getFilter(position).filterName)
                    type = "text/plain"
                }
                startActivity(sendIntent)

            }
        })
    }

    private fun getProductList(): ArrayList<Product?> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo
        val productList: ArrayList<Product?> = arrayListOf()
        val category = ArrayList<String>()
        category.add("Audi")
        category.add("Bentley")
        category.add("bmw")
        category.add("chevrolet")
        category.add("Citroen")
        category.add("Dacia")
        category.add("Ford")
        category.add("Fiat")
        val productNames = arrayListOf(
            "Audi e-tron GT",
            " Audi A1",
            "ford fiesta 2000",
            "Citroen C4",
            "Fiat casero",
            "Dacia sanchez",
            "Bentley nadie va a leer esto",
            "bmw coche para ricos",
            "bmw coches para todavía más ricos",
            "Citroen run run"
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

    private fun getFilterList(): ArrayList<Filter> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo

        val filterList: ArrayList<Filter> = arrayListOf()

        filterList.add(Filter("Filtros"))
        filterList.add(Filter("Precio"))
        filterList.add(Filter("Marca y modelo"))
        filterList.add(Filter("Año"))
        return filterList
    }
}