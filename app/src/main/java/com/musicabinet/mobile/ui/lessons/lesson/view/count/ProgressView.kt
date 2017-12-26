package com.musicabinet.mobile.ui.lessons.lesson.view.count

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.textColor

class ProgressView : View {

    //Current progress
    private var currentProgress = 0f

    // The paint for the circle.
    private val circlePaint: Paint = Paint()
    private val progressPaint: Paint = Paint()
    private val tempRect = RectF()

    private var animationDuration: Int = 600000

    private var animation: ValueAnimator? = null
    private var shouldShowDialog = true

    constructor(context: Context?) : super(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet)
            : this(context, attrs, R.attr.progressBarStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int)
            : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        // Attribute initialization
        val circleColor = ContextCompat.getColor(getContext(), R.color.white)
        val progressColor = ContextCompat.getColor(getContext(), R.color.white)

        circlePaint.color = circleColor
        circlePaint.style = Paint.Style.STROKE
        circlePaint.isAntiAlias = true

        progressPaint.color = progressColor
        progressPaint.isAntiAlias = true

        startAnimating()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }


    private fun drawCircle(canvas: Canvas) {
        tempRect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawArc(tempRect, 0f, 360f, true, circlePaint)
        canvas.drawArc(tempRect, -90f, 360 * currentProgress / 100, true, progressPaint)
    }

    fun startAnimating() {
        animation = ValueAnimator.ofFloat(0f, 100f)
        animation?.duration = animationDuration.toLong()
        animation?.addUpdateListener { valueAnimator ->
            currentProgress = valueAnimator.animatedValue as Float
            invalidate()
        }

        animation?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                showProgressDialog()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animation?.start()

    }

    private fun showProgressDialog() {
        if (shouldShowDialog) {
            val alertDialog = AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                    .setMessage(R.string.progress_dialog_text)
                    .setPositiveButton(android.R.string.ok, null)
                    .create()

            alertDialog.show()
            shouldShowDialog = false
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).textColor(R.color.colorPrimary)
        }
    }

}