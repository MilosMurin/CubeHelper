package me.milos.murin.cubehelper.layouts.algs

import android.graphics.Color
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val algName: TextView = view.findViewById(R.id.algListName)
        val algAlg: TextView = view.findViewById(R.id.algListAlg)
        val cube: ImageView = view.findViewById(R.id.algListCube)
        val back: ConstraintLayout = view.findViewById(R.id.algListItemBack)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.alg_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val alg = data[position]
        val type = Algorithms.getType(position)
        val id = Algorithms.getId(position)

        holder.algName.text = Algorithms.algListName(type, id)
        holder.algAlg.text = alg.alg
        holder.cube.setImageDrawable(CubeDrawable(alg.layer))

        if (Algorithms.isInSelection(type, id)) {
            holder.back.setBackgroundColor(Color.GREEN)
        } else {
            holder.back.setBackgroundColor(Color.WHITE)
        }

        holder.back.setOnClickListener {
            select(holder.back, type, id)
        }

    }

    private fun select(layout: ConstraintLayout, type: String, id: Int) {
        if (Algorithms.select(type, id)) {
            layout.setBackgroundColor(Color.GREEN)
        } else {
            layout.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}