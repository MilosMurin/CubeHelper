package me.milos.murin.cubehelper.layouts.twoImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.databinding.FragmentTwoImageBinding

/**
 * Fragment zobrazujuci hlavne menu
 */
class TwoImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = DataBindingUtil.inflate<FragmentTwoImageBinding>(
            inflater, R.layout.fragment_two_image, container, false)


        binding.imageNoOne.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainTwoImage_to_timer)
        }

        binding.imageNoTwo.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_learnTwoImage_to_algOfTheDay)
        }

        return binding.root
    }


}