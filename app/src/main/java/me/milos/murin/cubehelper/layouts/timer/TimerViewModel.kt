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

/**
 * View model obashujuci data o casovaci
 *
 * @property timer casovac
 * @property scramble sucasne zadanie rozlozenia kocky
 * @property database databaza na ukladanie casov
 * @property _ready je casovac pripraveny?
 */
class TimerViewModel(private val database: SolveDatabaseDao, application: Application) : AndroidViewModel(application) {

    private lateinit var timer: Timer

    private lateinit var scramble: String

    private var _ready: Boolean = false
    var ready: Boolean
        get() = _ready
        set(value) {
            _ready = value
        }

    /**
     * Vytvori novy casovac
     */
    fun setTimer(view: TextView) {
        timer = Timer(view)
    }

    /**
     * Zastavy stopky a ulozi cas do databazy
     */
    fun stopTimer(): String? {
        val endTime = timer.stopTimer() ?: return null
        viewModelScope.launch {
            insert()
        }
        return endTime
    }

    /**
     * spusti casovac
     */
    fun startTimer() {
        ready = false
        timer.startTimer()
    }

    /**
     * Vygeneruje nove zadanie rozlozenia kocky
     */
    fun generateScramble(): String {
        scramble = ScrambleGenerator.generateScramble()
        return App.getStringResource(R.string.scramble, scramble)
    }

    /**
     * Vloazi cas do databazy
     */
    private suspend fun insert() {
        val solve = Solve()
        solve.solveTime = timer.getEndTime()
        solve.scramble = scramble
        database.insert(solve)
    }

    /**
     * Zisti pocet poskladani z databazy
     */
    suspend fun getNumOfSolves(): Long {
        return database.getNumOfSolves() ?: 0
    }

    /**
     * Zisti naj-lepsie/-horsie trvanie poskladania
     * @param best true-najlepsie trvanie, false - najhorsie trvanie
     */
    suspend fun getSolve(best: Boolean): Long {
        val solve: Long = if (best) {
            database.getBestSolve()
        } else {
            database.getWorstSolve()
        } ?: return 0
        return solve
    }

    /**
     * Zisti primer z daneho poctu poslednych poskladani
     * @param of pocet z ktoreho sa ma urobit priemer
     */
    private suspend fun getAverage(of: Int): Long {
        var avg: Long = if (of == 3) {
            database.getCompAverage().subList(1, 4).sum()
        } else {
            database.getAverageOf(of).sum()
        }
        avg /= of
        return avg
    }

    /**
     * Vytvori retazec z priemeru z databazy
     * Nadstavbova funkcia nad [getAverage]
     * @param of pocet z ktoreho sa ma urobit priemer
     */
    suspend fun averageString(of: Int): String {
        val nos = getNumOfSolves()
        if (of == 3) {
            return if (nos >= 5) {
                App.getStringResource(R.string.average_of_comp, Timer.getTimeString(getAverage(of)))
            } else {
                App.getStringResource(R.string.average_of_comp, Timer.getNullTime())
            }
        }
        return if (nos >= of) {
            App.getStringResource(R.string.average_of, of, Timer.getTimeString(getAverage(of)))
        } else {
            App.getStringResource(R.string.average_of, of, Timer.getNullTime())
        }
    }

}