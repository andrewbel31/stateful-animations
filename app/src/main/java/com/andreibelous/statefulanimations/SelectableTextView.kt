package com.andreibelous.statefulanimations

import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.widget.AppCompatTextView

class SelectableTextView
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attributeSet, defStyleAttr) {

    private var animatedTextColor =
        animatedColor(
            initial = Color.BLACK,
            animationSpec = AnimationSpec(
                duration = DURATION,
                interpolator = LinearInterpolator()
            )
        ) {
            setTextColor(it)
        }

    private var animatedBackgroundColor =
        animatedColor(
            initial = Color.LTGRAY,
            animationSpec = AnimationSpec(
                duration = DURATION,
                interpolator = LinearInterpolator()
            )
        ) {
            setBackgroundColor(it)
        }

    private var animatedScale =
        animatedFloat(
            initial = 1f,
            animationSpec = AnimationSpec(
                duration = DURATION,
                interpolator = OvershootInterpolator()
            )
        ) {
            scaleX = it
            scaleY = it
        }


    init {
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 20f)
            }
        }
    }

    fun bind(isSelected: Boolean) {
        animatedBackgroundColor.value = if (isSelected) Color.BLACK else Color.LTGRAY
        animatedTextColor.value = if (isSelected) Color.WHITE else Color.BLACK
        animatedScale.value = if (isSelected) 1.2f else 1f
    }

    override fun onDetachedFromWindow() {
        animatedBackgroundColor.dispose()
        animatedTextColor.dispose()
        super.onDetachedFromWindow()
    }

    private companion object {

        private const val DURATION = 150L
    }
}