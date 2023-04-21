package com.bus.buswaiting.base

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener


class ZoomImageView : androidx.appcompat.widget.AppCompatImageView {
    private var matrix: Matrix? = null
    private var scaleDetector: ScaleGestureDetector? = null
    private var gestureDetector: GestureDetector? = null
    private var scaleFactor = 1.0f

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        matrix = Matrix()
        scaleType = ScaleType.MATRIX
        scaleDetector = ScaleGestureDetector(context, ScaleListener())
        gestureDetector = GestureDetector(context, GestureListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleDetector!!.onTouchEvent(event!!)
        gestureDetector!!.onTouchEvent(event)
        return true
    }

    inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            val focusX = detector.focusX
            val focusY = detector.focusY
            matrix?.postScale(scaleFactor, scaleFactor, focusX, focusY)
            imageMatrix = matrix
            return true
        }
    }

    inner class GestureListener : SimpleOnGestureListener() {
        private val lastPoint = PointF()
        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (e1.pointerCount == 1 && e2.pointerCount == 1 && !scaleDetector!!.isInProgress) {
                matrix!!.postTranslate(-distanceX, -distanceY);
                imageMatrix = matrix;
                return true;
            }
            return false;
        }

        override fun onDown(e: MotionEvent): Boolean {
            return true;
        }
    }
}