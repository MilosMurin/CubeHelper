package me.milos.murin.cubehelper.layouts.timer

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.database.Solve
import me.milos.murin.cubehelper.database.SolveDatabaseDao
import me.milos.murin.cubehelper.helpers.ScrambleGenerator
import me.milos.murin.cubehelper.helpers.Timer

class TimerViewModel(private val database: SolveDatabaseDao, application: Application) : AndroidViewModel(application) {

    private lateinit var timer: Timer

    private lateinit var scramble: String

    private var _ready: Boolean = false
    var ready: Boolean
        get() = _ready
        set(value) {
            _ready = value
        }

    fun setTimer(view: TextView) {
        timer = Timer(view)
    }

    fun stopTimer(): String? {
        val endTime = timer.stopTimer() ?: return null
        viewModelScope.launch {
            insert()
        }
        return endTime
    }

    fun startTimer() {
        ready = false
        timer.startTimer()
    }

    fun generateScramble(): String {
        scramble = ScrambleGenerator.generateScramble()
        return App.get(R.string.scramble,scramble)
    }

    private suspend fun insert() {
        val solve = Solve()
        solve.solveTime = timer.getTime()
        solve.scramble = scramble
        database.insert(solve)
    }


    suspend fun getNumOfSolves(): Long {
        return database.getNumOfSolves() ?: 0
    }

    suspend fun getSolve(best: Boolean): Long {
        val solve: Long = if (best) {
            database.getBestSolve()
        } else {
            database.getWorstSolve()
        } ?: return 0
        return solve
    }

    private suspend fun getAverage(of: Int): Long {
        var avg: Long = if (of == 3) {
            database.getCompAverage().subList(1, 4).sum()
        } else {
            database.getAverageOf(of).sum()
        }
        avg /= of
        return avg
    }

    suspend fun averageString(of: Int): String {
        val nos = getNumOfSolves()
        if (of == 3) {
            return if (nos >= 5) {
                App.get(R.string.average_of_comp, Timer.getTimeString(getAverage(of)))
            } else {
                App.get(R.string.average_of_comp, Timer.getNullTime())
            }
        }
        return if (nos >= of) {
            App.get(R.string.average_of, of, Timer.getTimeString(getAverage(of)))
        } else {
            App.get(R.string.average_of, of, Timer.getNullTime())
        }
    }

}