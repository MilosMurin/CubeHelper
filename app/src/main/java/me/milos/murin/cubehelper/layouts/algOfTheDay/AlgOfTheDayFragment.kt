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

/**
 * Fragment zobrazujuci algoritmus dna
 */
class AlgOfTheDayFragment : Fragment() {

    private val viewModel: AOTDViewModel by viewModels()

    /**
     * Vytvori menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.aotd_menu, menu)
    }

    /**
     * Presmeruje uzivatela na vyber algoritmov
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.algSelection -> {
                view?.findNavController()?.navigate(R.id.action_algOfTheDayFragment_to_algListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Vytvori pohlad na zobrazenie a nastavi potrebne veci
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding =
            DataBindingUtil.inflate<FragmentAlgOfTheDayBinding>(inflater, R.layout.fragment_alg_of_the_day, container, false)

        setHasOptionsMenu(true)

        viewModel.updateAlgorithm()

        binding.aotdName.text = viewModel.algName

        binding.algorithmText.text = viewModel.algText
        binding.cubeOrientation.rotation = viewModel.rotation

        binding.cubeOrientation.setImageDrawable(viewModel.cubeDrawable)

        // ziskanie farby pozadia kocky
        val td = TypedValue()
        binding.root.context.theme.resolveAttribute(R.attr.linePaint, td, false)
        CubeDrawable.setLinePaint(td.data)

        return binding.root
    }
}