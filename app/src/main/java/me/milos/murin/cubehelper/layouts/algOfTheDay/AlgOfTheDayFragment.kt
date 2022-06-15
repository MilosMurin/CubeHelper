package me.milos.murin.cubehelper.layouts.algOfTheDay

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.databinding.FragmentAlgOfTheDayBinding
import me.milos.murin.cubehelper.helpers.CubeDrawable


class AlgOfTheDayFragment : Fragment() {

    private val viewModel: AOTDViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.aotd_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.algSelection -> {
                view?.findNavController()?.navigate(R.id.action_algOfTheDayFragment_to_algListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding =
            DataBindingUtil.inflate<FragmentAlgOfTheDayBinding>(inflater, R.layout.fragment_alg_of_the_day, container, false)

        setHasOptionsMenu(true)

        draw(binding)

        val td = TypedValue()
        binding.root.context.theme.resolveAttribute(R.attr.linePaint, td, false)
        CubeDrawable.setLinePaint(td.data)

//        For testing
//        binding.cubeOrientation.setOnClickListener {
//            viewModel.nextAlg()
//            draw(binding)
//        }

        return binding.root
    }

    private fun draw(binding: FragmentAlgOfTheDayBinding) {
        viewModel.updateAlgorithm()

        binding.aotdName.text = viewModel.algName

        binding.algorithmText.text = viewModel.algText
        binding.cubeOrientation.rotation = viewModel.rotation

        binding.cubeOrientation.setImageDrawable(viewModel.cubeDrawable)
    }
}