package me.milos.murin.cubehelper.layouts.algList

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.data.Algorithms
import me.milos.murin.cubehelper.helpers.CubeDrawable

class AlgorithmAdapter(private val data: List<Algorithms.Algorithm>):
    RecyclerView.Adapter<AlgorithmAdapter.ViewHolder>() {

    private var background: Int = 0
    private var green: Int = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val algName: TextView = view.findViewById(R.id.algListName)
        val algAlg: TextView = view.findViewById(R.id.algListAlg)
        val cube: ImageView = view.findViewById(R.id.algListCube)
        val back: ConstraintLayout = view.findViewById(R.id.algListItemBack)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.alg_list_item, viewGroup, false)
        val td = TypedValue()
        view.context.theme.resolveAttribute(android.R.attr.windowBackground, td, true)
        background = td.data
        view.context.theme.resolveAttribute(R.attr.green, td, false)
        green = td.data

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val alg = data[position]
        val type = Algorithms.getType(position)
        val id = Algorithms.getId(position)

        holder.algName.text = Algorithms.algListName(type, id)
        holder.algAlg.text = Algorithms.getEdited() ?: alg.alg
        holder.cube.setImageDrawable(CubeDrawable(alg.layer))

        if (Algorithms.isInSelection(type, id)) {
            holder.back.setBackgroundResource(green)
        } else {
            holder.back.setBackgroundColor(background)
        }

        holder.back.setOnClickListener {
            select(holder.back, type, id)
        }

        holder.algAlg.setOnFocusChangeListener { _: View, _: Boolean ->
            if (!holder.algAlg.hasFocus()) {
                Algorithms.edit(position, holder.algAlg.text.toString())
            }
        }
    }

    private fun select(layout: ConstraintLayout, type: String, id: Int) {
        if (Algorithms.select(type, id)) {
            layout.setBackgroundResource(green)
        } else {
            layout.setBackgroundColor(background)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}