package com.andreibelous.statefulanimations

import android.animation.ArgbEvaluator
import android.animation.FloatEvaluator
import android.animation.IntEvaluator

fun animatedColor(
    initial: Int,
    animationSpec: AnimationSpec,
    updateListener: (Int) -> Unit
): AnimatedValue<Int> {
    val evaluator = ArgbEvaluator()

    return AnimatedValue(
        initial = initial,
        spec = animationSpec,
        evaluation = { fraction, start, end ->
            evaluator.evaluate(fraction, start, end) as Int
        },
        updateCallback = updateListener
    )
}

fun animatedFloat(
    initial: Float,
    animationSpec: AnimationSpec,
    updateListener: (Float) -> Unit,
): AnimatedValue<Float> {
    val evaluator = FloatEvaluator()
    return AnimatedValue(
        initial = initial,
        spec = animationSpec,
        evaluation = { fraction, start, end -> evaluator.evaluate(fraction, start, end) },
        updateCallback = updateListener
    )
}

fun animatedInt(
    initial: Int,
    animationSpec: AnimationSpec,
    updateListener: (Int) -> Unit,
): AnimatedValue<Int> {
    val evaluator = IntEvaluator()
    return AnimatedValue(
        initial = initial,
        spec = animationSpec,
        evaluation = { fraction, start, end -> evaluator.evaluate(fraction, start, end) },
        updateCallback = updateListener
    )
}