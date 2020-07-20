package com.laixer.swabbr.presentation.recording

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import com.laixer.swabbr.R
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class TimerView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    var mTimerStart = 0L
    private var mTimerThread: ScheduledExecutorService? = null
    private var events = ArrayList<Pair<Pair<Int, Int>, () -> Unit>>()
    private var progressBarList: ArrayList<Pair<ProgressBar, () -> Unit>> = ArrayList()

    interface TimerProvider {
        fun getTimecode(): Long
        fun getDuration(): Long
    }

    private lateinit var mTimerProvider: TimerProvider
    private var mDefaultTimerProvider: TimerProvider

    fun setTimerProvider(timerProvider: TimerProvider) {
        mTimerProvider = timerProvider
    }

    constructor(context: Context) : this(context, null)

    init {
        mDefaultTimerProvider = object : TimerProvider {
            override fun getTimecode(): Long = System.currentTimeMillis() - mTimerStart
            override fun getDuration(): Long = -1L
        }
    }

    fun addProgressBar(progressBar: ProgressBar, onFinish: () -> Unit) {
        progressBarList.add(Pair(progressBar, onFinish))
    }

    @Synchronized
    fun startTimer(progressbar: ProgressBar, refreshInterval: Long = DEFAULT_REFRESH_INTERVAL) {
        if (mTimerThread != null) return
        if (!::mTimerProvider.isInitialized) mTimerProvider = mDefaultTimerProvider

        progressBarList.map { it.first.progress = 0 }

        text = context.resources.getString(R.string.zero_time)

        mTimerStart = System.currentTimeMillis()
        mTimerThread = Executors.newSingleThreadScheduledExecutor()
        mTimerThread?.scheduleWithFixedDelay({
            Handler(Looper.getMainLooper()).post {
                val durationMs = mTimerProvider.getDuration()
                val timecodeMs = mTimerProvider.getTimecode()
                val timecodeTotalSeconds = timecodeMs / MILLISECONDS_PER_SECOND
                val timecodeMinutes = ((timecodeTotalSeconds / SECONDS_PER_MINUTE) % SECONDS_PER_MINUTE).toInt()
                val timecodeSeconds = (timecodeTotalSeconds % SECONDS_PER_MINUTE).toInt()

                text = genTimerDisplay(
                    durationMs, timecodeMs, timecodeSeconds, timecodeMinutes
                )

                progressBarList.map {
                    it.first.progress = timecodeMs.toInt() / 100
                    if (it.first.progress >= it.first.max) {
                        it.second()
                    }
                }

                progressBarList = progressBarList.filter { it.first.progress < it.first.max } as ArrayList

                events.filter { it.first.first == timecodeMinutes && it.first.second == timecodeSeconds }
                    .map { it.second() }
            }
        }, refreshInterval, refreshInterval, TimeUnit.MILLISECONDS)

        visibility = View.VISIBLE
    }

    @Synchronized
    fun resetEvents() {
        events = ArrayList()
    }

    @Synchronized
    fun addEventAt(minute: Int, second: Int, func: () -> Unit) {
        events.add((Pair(Pair(minute, second), func)))
    }

    @Synchronized
    fun removeEventAt(minute: Int, second: Int) {
        events = events.filter { it.first.first != minute && it.first.first != second } as ArrayList
    }

    @Synchronized
    fun stopTimer() {
        if (mTimerThread == null) return

        resetEvents()

        mTimerThread?.shutdown()
        mTimerThread = null

        visibility = View.INVISIBLE
        text = context.resources.getString(R.string.zero_time)
    }

    @Synchronized
    fun isRunning(): Boolean = mTimerThread != null

    private fun genTimerDisplay(
        durationMs: Long,
        timecodeMs: Long,
        timecodeSeconds: Int,
        timecodeMinutes: Int
    ): String {
        if (durationMs > 0L && durationMs >= timecodeMs) {
            val durationTotalSeconds = durationMs / MILLISECONDS_PER_SECOND

            return String.format(
                "%02d:%02d / %02d:%02d",
                timecodeMinutes,
                timecodeSeconds,
                (durationTotalSeconds / SECONDS_PER_MINUTE) % SECONDS_PER_MINUTE,
                durationTotalSeconds % SECONDS_PER_MINUTE
            )
        }
        return String.format("%02d:%02d", timecodeMinutes, timecodeSeconds)
    }

    companion object {
        const val DEFAULT_REFRESH_INTERVAL = 100L
        const val MILLISECONDS_PER_SECOND = 1000L
        const val SECONDS_PER_MINUTE = 60
    }
}
