package me.milos.murin.cubehelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.databinding.FragmentAlgOfTheDayBinding


private const val AOTD_NAME_KEY = "aotd_name"
private const val AOTD_KEY = "aotd"

class AlgOfTheDayFragment : Fragment() {

    private var aotdName: String? = null;
    private var aotd: String? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            aotdName = it.getString(AOTD_NAME_KEY)
            aotd = it.getString(AOTD_KEY)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentAlgOfTheDayBinding>(inflater,
            R.layout.fragment_alg_of_the_day,container,false)

        setHasOptionsMenu(true)

        if (aotdName != null && aotd != null) {
            binding.aotdName.text = aotdName!!
            binding.algorithmText.text = aotd!!
        }

        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance(aotdName: String, aotd: String) = AlgOfTheDayFragment().apply {
            arguments = Bundle().apply {
                putString(AOTD_NAME_KEY, aotdName)
                putString(AOTD_KEY, aotd)
            }
        }
    }
}