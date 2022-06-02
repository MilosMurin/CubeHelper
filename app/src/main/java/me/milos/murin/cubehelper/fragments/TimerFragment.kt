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
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.helpers.ScrambleGenerator
import me.milos.murin.cubehelper.helpers.Timer
import me.milos.murin.cubehelper.helpers.Timer.Companion.getNullTime
import me.milos.murin.cubehelper.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private lateinit var timer: Timer
    private var ready: Boolean = false
    private lateinit var binding: FragmentTimerBinding
    private var solvesAmount = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)

        timer = Timer(fragment = this)


        binding.scramble.text = generateScramble()

        setTimes()


        binding.timerBack.setOnClickListener { view: View ->
            run {
               stopTimer()
                // TODO: Saving times, calculating averages
            }
        }

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
        val endTime = timer.stopTimer()
        if (endTime != null) {
            binding.timer.text = endTime
        }
        binding.timerBack.setBackgroundColor(Color.WHITE)
        changeVisibility(true)
        binding.tapAndHold.text = getString(R.string.timer_info)
        binding.scramble.text = generateScramble()
        binding.numOfSolves.text = getString(R.string.num_of_solves, ++solvesAmount)
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

    private fun setTimes() {
        // TODO: load saved times
        binding.timer.text = timer.toString()
        binding.numOfSolves.text = getString(R.string.num_of_solves, solvesAmount)
        binding.bestSolve.text = getString(R.string.best_solve, getNullTime())
        binding.worstSolve.text = getString(R.string.worst_solve, getNullTime())

        binding.averageOf5.text = averageString(5)
        binding.averageOf12.text = averageString(12)
        binding.averageOf50.text = averageString(50)
        binding.averageOf100.text = averageString(100)
        binding.averageOf5Comp.text = getString(R.string.average_of_comp, getNullTime())
    }

    private fun averageString(of: Int): String {
        return getString(R.string.average_of, of, getNullTime())
    }

}