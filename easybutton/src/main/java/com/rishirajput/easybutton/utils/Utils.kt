package com.rishirajput.easybutton.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

class Utils {
    companion object {
        fun parseDpValues(yourdpmeasure: Int, context: Context): Int {
            val r: Resources = context.getResources()
            return TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    yourdpmeasure.toFloat(),
                    r.displayMetrics
            ).toInt()
        }
    }
}