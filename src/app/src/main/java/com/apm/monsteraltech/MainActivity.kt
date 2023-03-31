package com.apm.monsteraltech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.apm.monsteraltech.databinding.MainActivityBinding
import com.apm.monsteraltech.ui.add.AddFragment
import com.apm.monsteraltech.ui.fav.FavFragment
import com.apm.monsteraltech.ui.home.HomeFragment
import com.apm.monsteraltech.ui.profile.ProfileFragment
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "Mensaje a imprimir");

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Para la primera vez que lo carga necesitamos esto
        replaceFragment(HomeFragment())
        setSupportActionBar(findViewById(R.id.my_toolbar))

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            Log.d("Item", item.toString());
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(fragment = HomeFragment())
                }
                R.id.fav -> {
                    Log.d("Fav", "CargoFav");
                    replaceFragment(fragment = FavFragment())
                }
                R.id.add -> {
                    replaceFragment(fragment = AddFragment())

                }
                R.id.profile -> {
                    replaceFragment(fragment = ProfileFragment())
                }
                else -> {
                    //TODO: Poner algo aquí
                }
            }
            true // Devuelve true si el elemento ha sido seleccionado correctamente, o false si no ha sido seleccionado
        }
    }


    private fun replaceFragment(fragment : Fragment){
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}