package com.andreibelous.statefulanimations

import android.graphics.Color
import android.graphics.Outline
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Button
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circle = findViewById<View>(R.id.circle).apply {
            clipToOutline = true
            outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setOval(0, 0, view.width, view.height)
                }
            }
            setBackgroundColor(Color.RED)
        }
        val centerCircle =
            animatedFloat(
                0f,
                animationSpec = AnimationSpec(
                    300,
                    interpolator = OvershootInterpolator()
                )
            ) {
                circle.x = it
            }

        val progress = findViewById<LinearProgressIndicator>(R.id.progress)

        val animatedProgress = animatedInt(
            initial = 0,
            animationSpec = AnimationSpec(
                duration = 300,
                interpolator = LinearInterpolator()
            ),
        ) {
            progress.progress = it
        }

        val button1 = findViewById<Button>(R.id.button1).apply {
            setOnClickListener {
                animatedProgress.value = (animatedProgress.value - 20).coerceAtLeast(0)
            }
        }

        val button2 = findViewById<Button>(R.id.button2).apply {
            setOnClickListener {
                animatedProgress.value = (animatedProgress.value + 20).coerceAtLeast(0)
            }
        }

        val tab1 = findViewById<SelectableChipView>(R.id.tab1).apply {
            bind(false)
        }
        val tab2 = findViewById<SelectableChipView>(R.id.tab2).apply {
            bind(false)
        }
        val tab3 = findViewById<SelectableChipView>(R.id.tab3).apply {
            bind(false)
        }

        tab1.setOnClickListener {
            tab1.bind(true)
            tab2.bind(false)
            tab3.bind(false)
            centerCircle.value = tab1.x + tab1.width / 2 - circle.width / 2
        }

        tab2.setOnClickListener {
            tab1.bind(false)
            tab2.bind(true)
            tab3.bind(false)
            centerCircle.value = tab2.x + tab2.width / 2 - circle.width / 2
        }

        tab3.setOnClickListener {
            tab1.bind(false)
            tab2.bind(false)
            tab3.bind(true)
            centerCircle.value = tab3.x + tab3.width / 2 - circle.width / 2

        }
    }
}