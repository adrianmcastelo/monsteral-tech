package com.apm.monsteraltech

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

class ProductDetail : ActionBarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val t: Toolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(t)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // llama a la función onBackPressed() de la actividad para volver a la actividad anterior
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}