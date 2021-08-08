package com.andreibelous.statefulanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tab1 = findViewById<SelectableTextView>(R.id.tab1).apply {
            bind("Profile", false)
        }
        val tab2 = findViewById<SelectableTextView>(R.id.tab2).apply {
            bind("Safety Center", false)
        }
        val tab3 = findViewById<SelectableTextView>(R.id.tab3).apply {
            bind("Other", false)
        }

        tab1.setOnClickListener {
            tab1.bind("Profile", true)
            tab2.bind("Safety Center", false)
            tab3.bind("Other", false)
        }

        tab2.setOnClickListener {
            tab1.bind("Profile", false)
            tab2.bind("Safety Center", true)
            tab3.bind("Other", false)
        }

        tab3.setOnClickListener {
            tab1.bind("Profile", false)
            tab2.bind("Safety Center", false)
            tab3.bind("Other", true)
        }
    }
}