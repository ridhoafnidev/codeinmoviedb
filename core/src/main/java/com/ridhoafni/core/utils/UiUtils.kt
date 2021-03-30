package com.ridhoafni.core.utils

import android.content.Context
import android.util.TypedValue
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat

object UiUtils {
    // convert dip to float
    fun dipToPixels(context: Context?, dipValue: Float): Float {
        val metrics = context?.resources?.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    }
}