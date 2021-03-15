package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0

    private val valueAnimator = ValueAnimator()

    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

    }

    private val paintRect = Paint(Paint.ANTI_ALIAS_FLAG)

    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 50.0f
    }


    private var notLoadingColor = 0
    private var loadingColor = 0

    init {
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            notLoadingColor = getColor(R.styleable.LoadingButton_notLoadingColor, 0)
            loadingColor = getColor(R.styleable.LoadingButton_loadingColor, 0)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paintRect.color = notLoadingColor
        paintText.color = Color.WHITE

        val rect = RectF(0F, 0F, width.toFloat(), height.toFloat())

        canvas?.drawRect(rect, paintRect)
        canvas?.drawText("Download", rect.centerX(), rect.centerY().plus(16), paintText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

}