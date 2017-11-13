package com.musicabinet.mobile.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author Kirchhoff-
 */
class MultilineEllipsizedTextView : TextView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        viewTreeObserver.addOnGlobalLayoutListener {
            val maxLines = height / lineHeight
            setMaxLines(maxLines)
            viewTreeObserver.removeOnGlobalLayoutListener { }
        }
    }
}