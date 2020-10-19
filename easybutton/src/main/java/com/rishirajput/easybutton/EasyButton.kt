package com.rishirajput.easybutton

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet

class EasyButton:androidx.appcompat.widget.AppCompatButton {

    private lateinit var easyDrawable: EasyDrawable

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)  {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.EasyButton, 0, 0)

        val buttonShape = a.getInt(R.styleable.EasyButton_backgroundShape, -1)
        val buttonType = a.getInt(R.styleable.EasyButton_backgroundType, -1)
        val cornerRadius = a.getDimensionPixelSize(R.styleable.EasyButton_cornerRadius, 0)
        val enableStroke = a.getBoolean(R.styleable.EasyButton_enableStroke, false)
        val strokeSize = a.getDimensionPixelSize(R.styleable.EasyButton_strokeSize, 0)
        val defaultBackGroundColor = a.getColor(R.styleable.EasyButton_defaultBackGroundColor, Color.GRAY)
        val defaultBackGroundColorTop = a.getColor(R.styleable.EasyButton_defaultBackGroundColorTop, defaultBackGroundColor)
        val defaultBackGroundColorBottom = a.getColor(R.styleable.EasyButton_defaultBackGroundColorBottom, defaultBackGroundColor)
        val pressedBackGroundColor = a.getColor(R.styleable.EasyButton_pressedBackGroundColor, Color.DKGRAY)
        val pressedBackGroundColorTop = a.getColor(R.styleable.EasyButton_pressedBackGroundColorTop, pressedBackGroundColor)
        val pressedBackGroundColorBottom = a.getColor(R.styleable.EasyButton_pressedBackGroundColorBottom, pressedBackGroundColor)
        a.recycle()
        easyDrawable = EasyDrawable(context)
                .setShape(buttonShape)
                .setBackgroundType(buttonType)
                .setCornerRadius(cornerRadius)
                .setCornerRadiusVisibility(cornerRadius>0)
                .setUnSelectedColors(intArrayOf(defaultBackGroundColorTop, defaultBackGroundColorBottom))
                .setSelectedColors(intArrayOf(pressedBackGroundColorTop, pressedBackGroundColorBottom))
                .setDisabledColors(intArrayOf(0,0,0,0))
                .setStrokeSize(if(enableStroke) (strokeSize) else 0)
                .setSelectedStrokeColor(if(enableStroke) Color.GREEN else Color.TRANSPARENT)
                .setUnSelectedStrokeColor(if(enableStroke) Color.RED else Color.TRANSPARENT)
                .setShadowColor(Color.DKGRAY)
                .setHaloColor(Color.TRANSPARENT)
        background = easyDrawable
    }

}