package com.apm.monsteraltech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar


interface Searchable {
    fun onSearch(query: String?)
}

abstract class ActionBarActivity : AppCompatActivity() {
    var menu: Menu? = null
    lateinit var toolbar:  Toolbar

    fun setUpSearchWithBack(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_bar)

        toolbar = findViewById(R.id.my_toolbar)

        // Establecer la Toolbar como la ActionBar
        setSupportActionBar(toolbar)
    }


}