package me.milos.murin.cubehelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.databinding.FragmentTwoImageBinding


class TwoImageFragment : Fragment() {

    private val args: TwoImageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentTwoImageBinding>(
            inflater, R.layout.fragment_two_image, container, false)

        if (args.page == 2) { // TODO: add text

            binding.imageNoOne.setImageResource(R.drawable.calendar)
            binding.imageNoTwo.setImageResource(R.drawable.cube)

            binding.imageNoTwo.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_twoImage_to_learnTwoImage)
            }

            binding.imageNoOne.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_learnTwoImage_to_algOfTheDay)
            }

        } else {
            binding.imageNoOne.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_mainTwoImage_to_timer)
            }

            binding.imageNoTwo.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_twoImage_to_learnTwoImage)
            }

            binding.imageNoOne.setImageResource(R.drawable.stopwatch)
            binding.imageNoTwo.setImageResource(R.drawable.book)
        }

        return binding.root
    }


}