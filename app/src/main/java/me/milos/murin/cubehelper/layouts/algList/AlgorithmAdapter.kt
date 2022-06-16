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

/**
 * Trieda vyuzivana na adaptovanie dat z recycler view
 */
class AlgorithmAdapter(private val data: List<Algorithms.Algorithm>):
    RecyclerView.Adapter<AlgorithmAdapter.ViewHolder>() {

    // Farby na zafarbovanie pozadia algoritmov podla vyberu
    private var background: Int = 0
    private var green: Int = 0

    /**
     * Trieda obshujuca pohlady pre jednotlive algoritmy
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val algName: TextView = view.findViewById(R.id.algListName)
        val algAlg: TextView = view.findViewById(R.id.algListAlg)
        val cube: ImageView = view.findViewById(R.id.algListCube)
        val back: ConstraintLayout = view.findViewById(R.id.algListItemBack)
    }

    /**
     * Vytvori viewHolder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Vytvori pohlad z alg_list_itemu
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.alg_list_item, viewGroup, false)
        // nacita farby podla temy
        val td = TypedValue()
        view.context.theme.resolveAttribute(android.R.attr.windowBackground, td, true)
        background = td.data
        view.context.theme.resolveAttribute(R.attr.green, td, false)
        green = td.data

        return ViewHolder(view)
    }

    /**
     * Nastavi hodnoty v layoute
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val alg = data[position]
        val type = Algorithms.getType(position)
        val id = Algorithms.getId(position)

        holder.algName.text = Algorithms.algListName(type, id)
        holder.algAlg.text = Algorithms.getEdited() ?: alg.alg
        holder.cube.setImageDrawable(CubeDrawable(alg.layer))

        // Nastavi farbu pozadia podla toho ci je algoritmus vo vybere alebo nie
        if (Algorithms.isInSelection(type, id)) {
            holder.back.setBackgroundResource(green)
        } else {
            holder.back.setBackgroundResource(background)
        }

        // Click listener na pridanie/odobratie algoritmu z vyberu
        holder.back.setOnClickListener {
            select(holder.back, type, id)
        }

        // Focus listener na upravovanie algoritmov uzivatelom
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

    /**
     * Vrati pocet algoritmov v zozname
     * Potrebne pre recycler view
     */
    override fun getItemCount() = data.size

}