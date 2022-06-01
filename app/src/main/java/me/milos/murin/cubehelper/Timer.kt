package me.milos.murin.cubehelper

import android.os.Handler
import android.os.Looper
import android.widget.TextView

class Timer(private var time: Long = 0, private val timer: TextView) {

    private var timerRunning: Boolean = false
    private var handler: Handler = Handler(Looper.getMainLooper())

    private lateinit var runnable: Runnable
    init {
        runnable = Runnable {
            if (timerRunning) {
                timer.text = getDiff(System.currentTimeMillis())

                handler.postDelayed(runnable, 1)
            }
        }
    }

    fun startTimer() {
        timerRunning = true
        resetTimer()
        handler.postDelayed(runnable, 1)
    }

    fun stopTimer() {
        if (timerRunning) {
            timerRunning = false
            handler.removeCallbacks(runnable)
            timer.text = getDiff(System.currentTimeMillis())
        }
    }

    fun resetTimer() {
        time = System.currentTimeMillis()
    }

    fun getDiff(oTime: Long): String {
        return getTimeString(oTime - time)
    }
    override fun toString(): String {
        return getTimeString(time)
    }


    companion object {
        fun getTimeString(time: Long): String {
            val miliSeconds = time % 1000
            val seconds = (time / 1000) % 60
            val minutes = ((time / 1000) / 60) % 60

            return String.format("%02d:%02d,%03d", minutes, seconds, miliSeconds)
        }


        fun getNullTime(): String {
            return getTimeString(0)
        }
    }


}