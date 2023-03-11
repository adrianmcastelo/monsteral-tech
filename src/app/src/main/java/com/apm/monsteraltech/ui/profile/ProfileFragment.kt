package com.apm.monsteraltech.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.apm.monsteraltech.R
import androidx.fragment.app.FragmentManager

import com.apm.monsteraltech.ui.home.HomeFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var btnproducts: Button? = null
    private var btnTransactions: Button? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        requireFragmentManager().beginTransaction().replace(R.id.contenedor_profile, ProfileProductsFragment()).commit()

        // Inicializa los botones
        btnproducts = view.findViewById(R.id.products_button)
        btnTransactions = view.findViewById(R.id.transacciones_button)

        // Crea una instancia del OnClickListener para reutilizar la misma lógica en ambos botones
        val onClickListener = View.OnClickListener { view ->
            var transaction = requireFragmentManager().beginTransaction()
            when(view.id){
                R.id.products_button -> {
                    transaction.replace(R.id.contenedor_profile, ProfileProductsFragment()).commit()
                }
                R.id.transacciones_button -> {
                    transaction.replace(R.id.contenedor_profile, ProfileTransactionsFragment()).commit()
                }
            }
        }

        // Asigna el mismo OnClickListener a los dos botones
        btnproducts?.setOnClickListener(onClickListener)
        btnTransactions?.setOnClickListener(onClickListener)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}