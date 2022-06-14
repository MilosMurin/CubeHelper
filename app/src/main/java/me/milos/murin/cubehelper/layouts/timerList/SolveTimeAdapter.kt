package me.milos.murin.cubehelper.layouts.timerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.database.Solve
import me.milos.murin.cubehelper.helpers.Timer
import java.text.DateFormat
import java.util.*

class SolveTimeAdapter(private var data: List<Solve>, private val fragment: TimerListFragment):
    RecyclerView.Adapter<SolveTimeAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val solveId: TextView = view.findViewById(R.id.timeListId)
        val solvePos: TextView = view.findViewById(R.id.timeListPosition)
        val solveDate: TextView = view.findViewById(R.id.timeListDate)
        val solveScramble: TextView = view.findViewById(R.id.timeListScramble)
        val solveTime: TextView = view.findViewById(R.id.timeListTime)
        val remove: ImageView = view.findViewById(R.id.timeListRemove)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.time_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val solve = data[position]

        holder.solveId.text = solve.solveId.toString()
        holder.solvePos.text = (position + 1).toString()
        val format: DateFormat = DateFormat.getDateTimeInstance()
        holder.solveDate.text = format.format(Date(solve.dateTime))
        holder.solveScramble.text = solve.scramble
        holder.solveTime.text = Timer.getTimeString(solve.solveTime)

        holder.remove.setOnClickListener {
            removeSolve(solve, position)
        }
    }

    private fun removeSolve(solve: Solve, position: Int) {
        fragment.removeSolve(solve, this)
        notifyItemRemoved(position)
    }

    override fun getItemCount() = data.size

    fun update(list: List<Solve>) {
        data = list
    }

}