package me.milos.murin.cubehelper.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.database.Solve
import me.milos.murin.cubehelper.database.SolveDatabase
import me.milos.murin.cubehelper.database.SolveDatabaseDao
import me.milos.murin.cubehelper.helpers.ScrambleGenerator
import me.milos.murin.cubehelper.helpers.Timer
import me.milos.murin.cubehelper.helpers.Timer.Companion.getNullTime
import me.milos.murin.cubehelper.databinding.FragmentTimerBinding
import me.milos.murin.cubehelper.helpers.Timer.Companion.getTimeString

class TimerFragment : Fragment() {

    private lateinit var timer: Timer
    private var ready: Boolean = false
    private lateinit var binding: FragmentTimerBinding

    private lateinit var database: SolveDatabaseDao

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)

        timer = Timer(fragment = this)

        database = SolveDatabase.getInstance(requireNotNull(this.activity).application).solveDatabaseDao


        binding.scramble.text = generateScramble()
        binding.timer.text = getNullTime()

        retrieveTimes()


        binding.timerBack.setOnClickListener { view: View -> stopTimer() }

        binding.timerBack.setOnLongClickListener {
            if (!ready) {
                ready = true
                binding.timerBack.setBackgroundColor(Color.YELLOW)
                binding.timer.text = getNullTime()
            }
            return@setOnLongClickListener true
        }

        binding.timerBack.setOnTouchListener { view, event ->
            run {
                if (event.action == MotionEvent.ACTION_UP) {
                    startTimer()
                }
                return@run view?.onTouchEvent(event) ?: true
            }
        }

        return binding.root
    }

    private fun startTimer() {
        if (ready) {
            ready = false
            binding.timerBack.setBackgroundColor(Color.GREEN)
            timer.startTimer()
            changeVisibility(false)
            binding.tapAndHold.text = getString(R.string.timer_running_info)
        }
    }

    private fun stopTimer() {
        val endTime = timer.stopTimer() ?: return
        binding.timer.text = endTime
        binding.timerBack.setBackgroundColor(Color.WHITE)
        changeVisibility(true)
        binding.tapAndHold.text = getString(R.string.timer_info)
        binding.scramble.text = generateScramble()
        saveTime()
        retrieveTimes()
    }

    private fun saveTime() {
        lifecycleScope.launch {
            insert()
        }
    }

    private suspend fun insert() {
        val solve = Solve()
        solve.solveTime = timer.getTime()
        solve.scramble = binding.scramble.text.toString()
        database.insert(solve)
    }

    fun setTimer(timer: String) {
        binding.timer.text = timer
    }


    private fun generateScramble(): String {
        return getString(R.string.scramble, ScrambleGenerator.generateScramble())
    }

    private fun changeVisibility(to: Boolean) {
        binding.allSolves.isVisible = to
        binding.pastTimes.isVisible = to
        binding.scramble.isVisible = to
    }

    private fun retrieveTimes() {

        lifecycleScope.launch {
            binding.numOfSolves.text = getNumOfSolves().toString()

            binding.bestSolve.text = getString(R.string.best_solve, getTimeString(getSolve(true)))
            binding.worstSolve.text = getString(R.string.worst_solve, getTimeString(getSolve(false)))

            binding.averageOf5.text = averageString(5)
            binding.averageOf12.text = averageString(12)
            binding.averageOf50.text = averageString(50)
            binding.averageOf100.text = averageString(100)
            binding.averageOf5Comp.text = averageString(3)
        }
    }

    private suspend fun getNumOfSolves(): Long {
        return database.getNumOfSolves() ?: 0
    }

    private suspend fun getSolve(best: Boolean): Long {
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

    private suspend fun averageString(of: Int): String {
        val nos = getNumOfSolves()
        if (of == 3) {
            if (nos >= 5) {
                return getString(R.string.average_of_comp, getTimeString(getAverage(of)))
            }
        }
        return if (nos >= of) {
            getString(R.string.average_of, of, getTimeString(getAverage(of)))
        } else {
            getString(R.string.average_of, of, getNullTime())
        }
    }

}