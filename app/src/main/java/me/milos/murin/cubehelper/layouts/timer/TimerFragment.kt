package me.milos.murin.cubehelper.layouts.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.launch
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.database.SolveDatabase
import me.milos.murin.cubehelper.databinding.FragmentTimerBinding
import me.milos.murin.cubehelper.helpers.Timer.Companion.getNullTime
import me.milos.murin.cubehelper.helpers.Timer.Companion.getTimeString


class TimerFragment : Fragment() {

    private lateinit var viewModel: TimerViewModel

    private lateinit var binding: FragmentTimerBinding

    private var default: Int = 0
    private var green: Int = 0
    private var yellow: Int = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)

        val td = TypedValue()
        binding.root.context.theme.resolveAttribute(android.R.attr.windowBackground, td, true)
        default = td.data
        binding.root.context.theme.resolveAttribute(R.attr.green, td, false)
        green = td.data
        binding.root.context.theme.resolveAttribute(R.attr.yellow, td, false)
        yellow = td.data


        val application = requireNotNull(this.activity).application

        val dataSource = SolveDatabase.getInstance(application).solveDatabaseDao
        val viewModelFactory = TimerViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory)[TimerViewModel::class.java]

        viewModel.setTimer(binding.timer)

        binding.scramble.text = viewModel.generateScramble()
        binding.timer.text = getNullTime()


        retrieveTimes()


        binding.timerBack.setOnClickListener { stopTimer() }

        binding.timerBack.setOnLongClickListener {
            readyTimer()
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

        binding.allSolves.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_timerFragment_to_timerListFragment)
        }

        return binding.root
    }

    private fun readyTimer() {
        if (!viewModel.ready) {
            viewModel.ready = true
            binding.timerBack.setBackgroundResource(yellow)
            binding.timer.text = getNullTime()
        }
    }

    private fun startTimer() {
        if (viewModel.ready) {
            binding.timerBack.setBackgroundResource(green)
            viewModel.startTimer()
            changeVisibility(false)
            binding.tapAndHold.text = getString(R.string.timer_running_info)
        }
    }

    private fun stopTimer() {
        val endTime = viewModel.stopTimer() ?: return
        binding.timer.text = endTime
        binding.timerBack.setBackgroundColor(default)
        changeVisibility(true)
        binding.tapAndHold.text = getString(R.string.timer_info)
        binding.scramble.text = viewModel.generateScramble()
        retrieveTimes()
    }

    private fun changeVisibility(to: Boolean) {
        binding.allSolves.isVisible = to
        binding.pastTimes.isVisible = to
        binding.scramble.isVisible = to
    }


    // Used for database access

    private fun retrieveTimes() {

        lifecycleScope.launch {
            val nos = viewModel.getNumOfSolves()
            binding.numOfSolves.text = nos.toString()
            binding.allSolves.isVisible = nos > 0

            binding.bestSolve.text = getString(R.string.best_solve, getTimeString(viewModel.getSolve(true)))
            binding.worstSolve.text = getString(R.string.worst_solve, getTimeString(viewModel.getSolve(false)))

            binding.averageOf5.text = viewModel.averageString(5)
            binding.averageOf12.text = viewModel.averageString(12)
            binding.averageOf50.text = viewModel.averageString(50)
            binding.averageOf100.text = viewModel.averageString(100)
            binding.averageOf5Comp.text = viewModel.averageString(3)
        }
    }

}