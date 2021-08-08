package com.andreibelous.statefulanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tab1 = findViewById<SelectableTextView>(R.id.tab1).apply {
            bind(false)
        }
        val tab2 = findViewById<SelectableTextView>(R.id.tab2).apply {
            bind(false)
        }
        val tab3 = findViewById<SelectableTextView>(R.id.tab3).apply {
            bind(false)
        }

        tab1.setOnClickListener {
            tab1.bind(true)
            tab2.bind(false)
            tab3.bind(false)
        }

        tab2.setOnClickListener {
            tab1.bind(false)
            tab2.bind(true)
            tab3.bind(false)
        }

        tab3.setOnClickListener {
            tab1.bind(false)
            tab2.bind(false)
            tab3.bind(true)
        }
    }
}