package me.milos.murin.cubehelper.layouts.algOfTheDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.databinding.FragmentAlgOfTheDayBinding


class AlgOfTheDayFragment : Fragment() {

    private val viewModel: AOTDViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding =
            DataBindingUtil.inflate<FragmentAlgOfTheDayBinding>(inflater, R.layout.fragment_alg_of_the_day, container, false)

        setHasOptionsMenu(true)

        draw(binding)

//        For testing
//        binding.cubeOrientation.setOnClickListener {
//            viewModel.nextAlg()
//            draw(binding)
//        }

        return binding.root
    }

    private fun draw(binding: FragmentAlgOfTheDayBinding) {

        binding.aotdName.text = viewModel.algName

        binding.algorithmText.text = viewModel.algText
        binding.cubeOrientation.rotation = viewModel.rotation

        binding.cubeOrientation.setImageDrawable(viewModel.cubeDrawable)
    }
}