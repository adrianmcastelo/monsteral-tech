package com.apm.monsteraltech.ui.home.filters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.apm.monsteraltech.ActionBarActivity
import com.apm.monsteraltech.R

class ElectronicFilterActivity : ActionBarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronic_filter)
        val t: Toolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(t)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}