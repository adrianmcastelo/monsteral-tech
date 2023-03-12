package com.apm.monsteraltech.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R

class ProfileFragment : Fragment() {
    private lateinit var btnProducts: Button
    private lateinit var btnTransactions: Button
    private lateinit var recyclerView: RecyclerView

    private val transactionList: ArrayList<Transactions> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inicializa los botones
        btnProducts = view.findViewById(R.id.products_button)
        btnTransactions = view.findViewById(R.id.transacciones_button)

        // Necesitamos configurar un Layout al Recycler para que funcione
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicialmente muestra la lista de productos
        showProductList()

        // Crea una instancia del OnClickListener para reutilizar la misma lógica en ambos botones
        val onClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.products_button -> showProductList()
                R.id.transacciones_button -> showTransactionList()
            }
        }

        // Asigna el OnClickListener a los botones
        btnProducts.setOnClickListener(onClickListener)
        btnTransactions.setOnClickListener(onClickListener)

        return view
    }

    private fun showProductList() {
        recyclerView.adapter = AdapterProductsData(getProductList())
    }

    private fun showTransactionList() {
        recyclerView.adapter = AdapterTransactionsData(getTransactionList())
    }

    private fun getTransactionList(): ArrayList<Transactions> {
        //TODO: Cargar los productos desde la base de datos o de otro recurso externo
        // Agrega algunas transacciones a la lista para mockear la respuesta
        for (i in 0 until 9) {
            val transaction = Transactions("Usuario $i", "Usuario ${i+1}", "ObjetoX", "xx/yy/zzzz")
            transactionList.add(transaction)
        }
        return  transactionList
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
