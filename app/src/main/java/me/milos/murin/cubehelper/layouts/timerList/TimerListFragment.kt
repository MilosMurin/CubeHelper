package me.milos.murin.cubehelper.layouts.timerList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.database.Solve
import me.milos.murin.cubehelper.database.SolveDatabase
import me.milos.murin.cubehelper.database.SolveDatabaseDao

/**
 * Fragment zobrazujuci zoznam poskladani pomocou recycler view
 */
class TimerListFragment : Fragment() {

    private lateinit var dataSource: SolveDatabaseDao

    private var recyclerView: RecyclerView? = null

    private lateinit var data: List<Solve>

    /**
     * Ziska databazu a data z nej
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = requireNotNull(this.activity).application

        dataSource = SolveDatabase.getInstance(application).solveDatabaseDao

        getSolves()

    }

    /**
     * Vytvori pohlad :)
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timer_list, container, false)

        if (recyclerView == null) {
            recyclerView = view?.findViewById(R.id.timerList)
        }

        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context, DividerItemDecoration.VERTICAL)

        recyclerView?.addItemDecoration(dividerItemDecoration)


        return view
    }

    /**
     * Ziska zoznam poskladani z databzy
     */
    private fun getSolves() {
        lifecycleScope.launch {
            data = dataSource.getSorted()

            val adapter = SolveTimeAdapter(data, this@TimerListFragment)

            if (recyclerView == null) {
                recyclerView = view?.findViewById(R.id.timerList)
            }

            recyclerView?.adapter = adapter
        }
    }

    /**
     * Vymaze zaznam v databaze
     */
    fun removeSolve(solve: Solve, adapter: SolveTimeAdapter) {
        lifecycleScope.launch {
            dataSource.delete(solve)
            adapter.update(dataSource.getSorted())
        }
    }

}