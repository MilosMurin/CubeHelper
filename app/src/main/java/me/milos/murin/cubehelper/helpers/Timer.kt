package me.milos.murin.cubehelper.helpers

import android.os.Handler
import android.os.Looper
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.fragments.TimerFragment

class Timer(private var time: Long = 0, private val fragment: TimerFragment) {

    private var timerRunning: Boolean = false
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var endTime: Long = 0

    private lateinit var runnable: Runnable

    init {
        runnable = Runnable {
            if (timerRunning) {

                fragment.setTimer(getDiff(System.currentTimeMillis()))

                handler.postDelayed(runnable, 1)
            }
        }
    }

    fun getTime(): Long {
        return endTime
    }

    fun startTimer() {
        timerRunning = true
        resetTimer()
        handler.postDelayed(runnable, 1)
    }

    fun stopTimer(): String? {
        if (timerRunning) {
            timerRunning = false
            handler.removeCallbacks(runnable)
            endTime = System.currentTimeMillis() - time
            return getTimeString(endTime)
        }
        return null
    }

    private fun resetTimer() {
        time = System.currentTimeMillis()
    }

    private fun getDiff(oTime: Long): String {
        return getTimeString(oTime - time)
    }

    override fun toString(): String {
        return getTimeString(time)
    }


    companion object {
        fun getTimeString(time: Long): String {
            val milliSeconds = time % 1000
            val seconds = (time / 1000) % 60
            val minutes = ((time / 1000) / 60) % 60

            return App.get(R.string.time_default, minutes, seconds, milliSeconds)
        }


        fun getNullTime(): String {
            return getTimeString(0)
        }
    }


}