package me.milos.murin.cubehelper.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.Timer
import me.milos.murin.cubehelper.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private var timerRunning: Boolean = false
    private var timer: Timer = Timer()
    private var held: Int = 0
    private var start: Boolean = false


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentTimerBinding>(
            inflater, R.layout.fragment_timer, container, false)

        binding.scramble.text = getString(R.string.scramble, generateScramble())

        setTimes(binding)

        return binding.root
    }

    private fun setTimes(binding: FragmentTimerBinding) {
        binding.timer.text = timer.toString()
        binding.lastSolve.text = getString(R.string.last_solve, Timer())
        binding.bestSolve.text = getString(R.string.best_solve, Timer())
        binding.worstSolve.text = getString(R.string.worst_solve, Timer())

        binding.averageOf5.text = averageString(5)
        binding.averageOf10.text = averageString(10)
        binding.averageOf12.text = averageString(12)
        binding.averageOf100.text = averageString(100)
        binding.averageOf1000.text = averageString(1000)
    }

    private fun generateScramble(): String {
        // TODO("Generate scrambles")
        return "R"
    }

    private fun averageString(of: Int): String {
        return getString(R.string.average_of, of, Timer())
    }

}