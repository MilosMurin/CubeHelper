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
import me.milos.murin.cubehelper.Timer
import me.milos.murin.cubehelper.Timer.Companion.getNullTime
import me.milos.murin.cubehelper.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private lateinit var timer: Timer
    private var start: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentTimerBinding>(
            inflater, R.layout.fragment_timer, container, false)

        timer = Timer(timer = binding.timer)


        binding.scramble.text = getString(R.string.scramble, generateScramble())

        setTimes(binding)



        binding.timerBack.setOnClickListener { view: View ->
            run {
                timer.stopTimer()
                binding.timerBack.setBackgroundColor(Color.WHITE)
                changeVisibility(binding, true)
                binding.tapAndHold.text = getString(R.string.timer_info)
                // TODO: Saving times, calculating averages
            }
        }

        binding.timerBack.setOnLongClickListener {
            if (!start) {
                start = true
                binding.timerBack.setBackgroundColor(Color.YELLOW)
            }
            return@setOnLongClickListener true
        }

        binding.timerBack.setOnTouchListener { view, event ->
            run {
                if (event.action == MotionEvent.ACTION_UP) {
                    if (start) {
                        start = false
                        binding.timerBack.setBackgroundColor(Color.GREEN)
                        timer.startTimer()
                        changeVisibility(binding, false)
                        binding.tapAndHold.text = getString(R.string.timer_running_info)
                    }
                }
                return@run view?.onTouchEvent(event) ?: true
            }
        }

        return binding.root
    }

    private fun generateScramble(): String {
        // TODO("Generate scrambles")
        return "TODO: Generate scrambles"
    }

    private fun changeVisibility(binding: FragmentTimerBinding, to: Boolean) {
        binding.scramble.isVisible = to
        binding.lastSolve.isVisible = to
        binding.bestSolve.isVisible = to
        binding.worstSolve.isVisible = to
        binding.averageOf5.isVisible = to
        binding.averageOf10.isVisible = to
        binding.averageOf12.isVisible = to
        binding.averageOf100.isVisible = to
        binding.averageOf1000.isVisible = to
    }

    private fun setTimes(binding: FragmentTimerBinding) {
        binding.timer.text = timer.toString()
        binding.lastSolve.text = getString(R.string.last_solve, getNullTime())
        binding.bestSolve.text = getString(R.string.best_solve, getNullTime())
        binding.worstSolve.text = getString(R.string.worst_solve, getNullTime())

        binding.averageOf5.text = averageString(5)
        binding.averageOf10.text = averageString(10)
        binding.averageOf12.text = averageString(12)
        binding.averageOf100.text = averageString(100)
        binding.averageOf1000.text = averageString(1000)
    }

    private fun averageString(of: Int): String {
        return getString(R.string.average_of, of, getNullTime())
    }

}