package com.example.assignment.extensions

import android.os.SystemClock
import android.view.View

class OnSingleClickListener(private val block: () -> Unit) : View.OnClickListener {

    private var lastClickTime = 0L

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 2000) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()

        block()
    }
}
