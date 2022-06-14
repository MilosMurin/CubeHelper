package me.milos.murin.cubehelper.layouts.algs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.data.Algorithms


class AlgListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.alg_list_fragment, container, false)

        val algs = Algorithms.getAllAlgs()
        val adapter = AlgorithmAdapter(algs)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.algList)

        recyclerView?.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(dividerItemDecoration)

        return view
    }

}