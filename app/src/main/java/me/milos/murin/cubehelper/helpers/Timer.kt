package me.milos.murin.cubehelper.helpers

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R

/**
 * Trieda obshujuca casovac
 * @property timerView textView kde mam zobrazovat svoj aktualny cas
 * @property startTime pociatocny cas stopiek
 * @property timerRunning bezia stopky?
 * @property handler pouziva sa na spustenie opakovaneho volania [runnable]
 * @property runnable co sa ma vykonat ked stopky bezia
 * @property endTime cas trvania spustenia stopiek, je nastaveny pri stopnuti stopiek na System cas - [startTime]
 */
class Timer(private val timerView: TextView) {

    private var startTime: Long = 0
    private var timerRunning: Boolean = false
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var endTime: Long = 0

    private lateinit var runnable: Runnable

    init {
        runnable = Runnable {
            if (timerRunning) { // ak stopky bezia tak upravim textView

                timerView.text = getDiff(System.currentTimeMillis())

                handler.postDelayed(runnable, 1)
            }
        }
    }

    /**
     * Getter na koncovy cas stopiek
     */
    fun getEndTime(): Long {
        return endTime
    }

    /**
     * Spusti casovac
     */
    fun startTimer() {
        timerRunning = true
        startTime = System.currentTimeMillis()
        handler.postDelayed(runnable, 1)
    }

    /**
     * Zastavi casovac
     *
     * @return retazec s trvanim spustenia stopiek, null - ak stopky nebezali
     */
    fun stopTimer(): String? {
        if (timerRunning) {
            timerRunning = false
            handler.removeCallbacks(runnable)
            endTime = System.currentTimeMillis() - startTime
            return getTimeString(endTime)
        }
        return null
    }

    /**
     * Vytvori retazec s rozdielom medzi danym casom a zaciatocnym casom
     */
    private fun getDiff(oTime: Long): String {
        return getTimeString(oTime - startTime)
    }

    override fun toString(): String {
        return getTimeString(endTime)
    }


    companion object {
        /**
         * Pomocna metoda na vytvorenie retazca a naformatovanie casu
         * @return retazec v tvare: mm:ss.msmsms (m-minutes,s-seconds,ms-miliseconds)
         */
        fun getTimeString(time: Long): String {
            val milliSeconds = time % 1000
            val seconds = (time / 1000) % 60
            val minutes = ((time / 1000) / 60) % 60

            return App.getStringResource(R.string.time_default, minutes, seconds, milliSeconds)
        }

        /**
         * Vytvori retazec s nulovym casom
         * @return retazec v tvare 00:00.000
         */
        fun getNullTime(): String {
            return getTimeString(0)
        }
    }


}