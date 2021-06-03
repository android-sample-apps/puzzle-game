package com.example.puzzle.util

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer

class PauseChronometer : Chronometer {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var pauseTime: Long = 0

    override fun start() {
        this.base = SystemClock.elapsedRealtime() - pauseTime
        super.start()
    }

    override fun stop() {
        pauseTime = SystemClock.elapsedRealtime() - this.base
        super.stop()
    }

    fun stringResult() = this.text.toString()
}
