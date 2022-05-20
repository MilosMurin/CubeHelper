package me.milos.murin.cubehelper.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.milos.murin.cubehelper.R

/**
 * A simple [Fragment] subclass.
 * Use the [TimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(me.milos.murin.cubehelper.ARG_PARAM1)
            param2 = it.getString(me.milos.murin.cubehelper.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Timer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {
                arguments = Bundle().apply {
                    putString(me.milos.murin.cubehelper.ARG_PARAM1, param1)
                    putString(me.milos.murin.cubehelper.ARG_PARAM2, param2)
                }
            }
    }
}