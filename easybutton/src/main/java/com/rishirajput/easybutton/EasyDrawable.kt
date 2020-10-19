package com.rishirajput.easybutton

import android.R
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import com.rishirajput.easybutton.utils.Utils

class EasyDrawable(context: Context) : Drawable() {

    companion object {
        const val SHAPE_OVAL: Int = 0
        const val SHAPE_RECTANGLE: Int = 1
        const val BACKGROUND_TYPE_SINGLE_COLOR = 0
        const val BACKGROUND_TYPE_GRADIENT_COLOR = 1
    }

    lateinit var unSelectedColors: IntArray
    lateinit var selectedColors: IntArray

    lateinit var mHaloPaint: Paint
    lateinit var mShadowPaint: Paint
    lateinit var mBackgroundPaint: Paint
    lateinit var mSelectedBackGroundPaint: Paint
    lateinit var mUnSelectedBackGroundPaint: Paint
    lateinit var mDisabledBackGroundPaint: Paint

    lateinit var mPath: Path

    var backGroundColor = 0
    var haloSize = 0
    var shadowOffsetX = 0
    var shadowOffsetY = 0

    private lateinit var radii: FloatArray
    private lateinit var disabledColors: IntArray

    var invalidate = false
    var selected = false
    var isPressed = false
    var disabled = false
    var showShadowInActiveState = false

    private var backgroundType = 0
    private var shape = 0
    private var cornerRadius = 0

    private var haloCenterX = 0f
    private var haloCenterY = 0f
    private var haloRadius = 0f
    private var backgroundCenterX = 0f
    private var backgroundCenterY = 0f
    private var backgroundRadius = 0f
    private var strokeRadius = 0f
    private var shadowCenterX = 0f
    private var shadowCenterY = 0f
    private var shadowRadius = 0f
    private var strokeSize = 0f

    private lateinit var haloRect: RectF
    private lateinit var haloPath: Path
    private lateinit var strokePath: Path
    private lateinit var backgroundRect: RectF
    private lateinit var strokeRect: RectF
    private lateinit var backGroundPath: Path
    private lateinit var shadowRect: RectF
    private lateinit var shadowPath: Path

    private lateinit var mUnSelectedStrokePaint: Paint
    private lateinit var mSelectedStrokePaint: Paint
    private lateinit var mStrokePaint: Paint

    init {
        mPath = Path()
        mPath.fillType = Path.FillType.EVEN_ODD

        haloSize = Utils.parseDpValues(2, context)
        shadowOffsetX = Utils.parseDpValues(1, context)
        shadowOffsetY = Utils.parseDpValues(1, context)

        mHaloPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mHaloPaint.style = Paint.Style.FILL

        mShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mShadowPaint.style = Paint.Style.FILL
        mShadowPaint.color = Color.parseColor("#FF0000")

        mUnSelectedBackGroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mUnSelectedBackGroundPaint.style = Paint.Style.FILL
        mSelectedBackGroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mSelectedBackGroundPaint.style = Paint.Style.FILL
        mDisabledBackGroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDisabledBackGroundPaint.style = Paint.Style.FILL
        mUnSelectedStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mUnSelectedStrokePaint.style = Paint.Style.STROKE
        mUnSelectedStrokePaint.color = Color.TRANSPARENT
        mSelectedStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mSelectedStrokePaint.style = Paint.Style.STROKE
        mSelectedStrokePaint.color = Color.TRANSPARENT

        mBackgroundPaint = mUnSelectedBackGroundPaint
        mStrokePaint = mUnSelectedStrokePaint

        haloRect = RectF()
        shadowRect = RectF()
        backgroundRect = RectF()
        strokeRect = RectF()

        haloPath = Path()
        backGroundPath = Path()
        shadowPath = Path()
        strokePath = Path()

        radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    }

    fun setBackgroundType(type: Int): EasyDrawable {
        backgroundType = type
        return this
    }

    fun setShape(shape: Int): EasyDrawable {
        this.shape = shape
        return this
    }

    fun setHaloColor(color: Int): EasyDrawable {
        mHaloPaint.color = color
        return this
    }

    fun setShadowColor(color: Int): EasyDrawable {
        mShadowPaint.color = color
        return this
    }

    fun setCornerRadius(radius: Int): EasyDrawable {
        cornerRadius = radius
        return this
    }

    fun setCornerRadiusVisibility(visibility: Boolean): EasyDrawable {
        setCornerRadiusVisibility(visibility, visibility, visibility, visibility)
        return this
    }

    fun setCornerRadiusVisibility(tl: Boolean, tr: Boolean, br: Boolean, bl: Boolean): EasyDrawable? {
        if (tl) {
            radii[0] = cornerRadius.toFloat()
            radii[1] = cornerRadius.toFloat()
        } else {
            radii[0] = 0f
            radii[1] = 0f
        }
        if (tr) {
            radii[2] = cornerRadius.toFloat()
            radii[3] = cornerRadius.toFloat()
        } else {
            radii[2] = 0f
            radii[3] = 0f
        }
        if (br) {
            radii[4] = cornerRadius.toFloat()
            radii[5] = cornerRadius.toFloat()
        } else {
            radii[4] = 0f
            radii[5] = 0f
        }
        if (bl) {
            radii[6] = cornerRadius.toFloat()
            radii[7] = cornerRadius.toFloat()
        } else {
            radii[6] = 0f
            radii[7] = 0f
        }
        return this
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val maximumRadius = Math.min(bounds.width(), bounds.height()) / 2f

        when (shape) {
            SHAPE_OVAL -> {
                haloCenterX = bounds.exactCenterX()
                haloCenterY = bounds.exactCenterY()
                haloRadius = maximumRadius
                backgroundCenterX = bounds.exactCenterX()
                backgroundCenterY = bounds.exactCenterY()
                backgroundRadius = maximumRadius - haloSize
                strokeRadius = backgroundRadius - strokeSize / 2
                shadowCenterX = bounds.exactCenterX() + shadowOffsetX
                shadowCenterY = bounds.exactCenterY() + shadowOffsetY
                shadowRadius = backgroundRadius
            }
            SHAPE_RECTANGLE -> {
                haloRect.left = bounds.left.toFloat()
                haloRect.top = bounds.top.toFloat()
                haloRect.right = bounds.right.toFloat()
                haloRect.bottom = bounds.bottom.toFloat()
                backgroundRect.left = haloRect.left + haloSize
                backgroundRect.top = haloRect.top + haloSize
                backgroundRect.right = haloRect.right - haloSize
                backgroundRect.bottom = haloRect.bottom - haloSize
                strokeRect.left = backgroundRect.left + strokeSize / 2
                strokeRect.right = backgroundRect.right - strokeSize / 2
                strokeRect.top = backgroundRect.top + strokeSize / 2
                strokeRect.bottom = backgroundRect.bottom - strokeSize / 2
                shadowRect.left = backgroundRect.left + shadowOffsetX
                shadowRect.right = backgroundRect.right + shadowOffsetX
                shadowRect.top = backgroundRect.top + shadowOffsetY
                shadowRect.bottom = backgroundRect.bottom + shadowOffsetY
                haloPath.reset()
                shadowPath.reset()
                backGroundPath.reset()
                strokePath.reset()
                haloPath.addRoundRect(haloRect, radii, Path.Direction.CW)
                shadowPath.addRoundRect(shadowRect, radii, Path.Direction.CW)
                backGroundPath.addRoundRect(backgroundRect, radii, Path.Direction.CW)
                strokePath.addRoundRect(strokeRect, radii, Path.Direction.CW)
            }
        }

        if (backgroundType == BACKGROUND_TYPE_GRADIENT_COLOR) {
            mUnSelectedBackGroundPaint.shader = LinearGradient(bounds.left.toFloat(), bounds.top.toFloat(), bounds.left.toFloat(), bounds.bottom.toFloat(), unSelectedColors[0], unSelectedColors[1], Shader.TileMode.CLAMP)
            mSelectedBackGroundPaint.shader = LinearGradient(bounds.left.toFloat(), bounds.top.toFloat(), bounds.left.toFloat(), bounds.bottom.toFloat(), selectedColors[0], selectedColors[1], Shader.TileMode.CLAMP)
            mDisabledBackGroundPaint.shader = LinearGradient(bounds.left.toFloat(), bounds.top.toFloat(), bounds.left.toFloat(), bounds.bottom.toFloat(), disabledColors[0], disabledColors[1], Shader.TileMode.CLAMP)
        }

    }

    override fun onStateChange(states: IntArray?): Boolean {
        invalidate = false
        selected = false
        disabled = true
        mBackgroundPaint = mUnSelectedBackGroundPaint
        mStrokePaint = mUnSelectedStrokePaint
        for (state in states!!) {
            if (state == R.attr.state_pressed || state == R.attr.state_selected || state == R.attr.state_activated) {
                selected = true
                mBackgroundPaint = mSelectedBackGroundPaint
                mStrokePaint = mSelectedStrokePaint
                invalidate = true
            } else if (state == -R.attr.state_enabled) {
                disabled = true
                invalidate = true
            } else if (state == R.attr.state_enabled) {
                disabled = false
                invalidate = true
            }
        }
        if (disabled) {
            mBackgroundPaint = mDisabledBackGroundPaint
        }
        return if (invalidate) invalidate else super.onStateChange(states)
    }

    override fun draw(canvas: Canvas) {
        when (shape) {
            SHAPE_OVAL -> {
                if (!disabled) {
                    canvas.drawCircle(haloCenterX, haloCenterY, haloRadius, mHaloPaint)
                }
                if (!selected && !disabled) {
                    canvas.drawCircle(shadowCenterX, shadowCenterY, shadowRadius, mShadowPaint)
                }
                canvas.drawCircle(backgroundCenterX, backgroundCenterY, backgroundRadius, mBackgroundPaint)
                canvas.drawCircle(backgroundCenterX, backgroundCenterY, strokeRadius, mStrokePaint)
            }
            SHAPE_RECTANGLE -> {
                if (!disabled) {
                    canvas.drawPath(haloPath, mHaloPaint)
                }
                if (showShadowInActiveState) {
                    canvas.drawPath(shadowPath, mShadowPaint)
                } else {
                    if (!selected && !disabled) {
                        canvas.drawPath(shadowPath, mShadowPaint)
                    }
                }
                canvas.drawPath(backGroundPath, mBackgroundPaint)
                canvas.drawPath(strokePath, mStrokePaint)
            }
        }
    }

    override fun setAlpha(p0: Int) {

    }

    override fun setColorFilter(p0: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun isStateful(): Boolean {
        return true
    }

    fun setSelectedColors(colors: IntArray): EasyDrawable {
        selectedColors = colors
        mSelectedBackGroundPaint.color = colors[0]
        return this
    }

    fun setUnSelectedColors(colors: IntArray): EasyDrawable {
        unSelectedColors = colors
        mUnSelectedBackGroundPaint.color = colors[0]
        return this
    }

    fun setDisabledColors(colors: IntArray): EasyDrawable {
        disabledColors = colors
        mDisabledBackGroundPaint.color = colors[0]
        return this
    }

    fun setStrokeSize(strokeSize: Int): EasyDrawable {
        this.strokeSize = strokeSize.toFloat()
        return this
    }

    fun setUnSelectedStrokeColor(strokeColor: Int): EasyDrawable {
        mUnSelectedStrokePaint.strokeWidth = strokeSize
        mUnSelectedStrokePaint.color = strokeColor
        return this
    }

    fun setSelectedStrokeColor(strokeColor: Int): EasyDrawable {
        mSelectedStrokePaint.strokeWidth = strokeSize
        mSelectedStrokePaint.color = strokeColor
        return this
    }

    fun showShadowInActiveState() {
        showShadowInActiveState = true
    }

}