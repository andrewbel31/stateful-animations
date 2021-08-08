package com.andreibelous.statefulanimations

import android.animation.ValueAnimator

class AnimatedValue<T>(
    initial: T,
    private val spec: AnimationSpec,
    private val updateCallback: (value: T) -> Unit,
    private val evaluation: (fraction: Float, startValue: T, endValue: T) -> T
) {

    private var animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = spec.duration
        interpolator = spec.interpolator
        addUpdateListener { currentValue = evaluation(it.animatedFraction, prevValue, value) }
    }

    init {
        updateCallback(initial)
    }

    var value: T = initial
        set(value) {
            if (field != value) {
                field = value
                animate(value)
            }
        }

    private var prevValue: T = value

    private var currentValue: T = initial
        set(value) {
            if (field != value) {
                field = value
                updateCallback(value)
            }
        }

    private fun animate(target: T) {
        if (currentValue == target) return // no animation needed in this case
        prevValue = currentValue

        with(animator) {
            val playedTime = currentPlayTime
            cancel()
            duration = if (playedTime > 0) {
                playedTime
            } else {
                spec.duration
            }

            start()
        }
    }

    fun dispose() {
        animator.cancel()
        animator.removeAllListeners()
    }
}