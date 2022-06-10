package me.milos.murin.cubehelper.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.data.Algorithms
import me.milos.murin.cubehelper.databinding.FragmentAlgOfTheDayBinding
import me.milos.murin.cubehelper.helpers.CubeDrawable


class AlgOfTheDayFragment : Fragment() {

    private lateinit var aotdType: String
    private var aotdId: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aotdType = listOf("pll", "oll").random()
        //aotdType = "pll"
        aotdId = if (aotdType.equals("pll")) Algorithms.getRandomPll()
        else Algorithms.getRandomOll()
        //aotdId = 1
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding =
            DataBindingUtil.inflate<FragmentAlgOfTheDayBinding>(inflater, R.layout.fragment_alg_of_the_day, container, false)

        setHasOptionsMenu(true)

        draw(binding)

//        binding.cubeOrientation.setOnClickListener {
//            run {
//                update()
//                draw(binding)
//            }
//        }

        return binding.root
    }

    private fun draw(binding: FragmentAlgOfTheDayBinding) {
        val alg: Algorithms.Algorithm = if (aotdType.equals("pll")) Algorithms.getPll(aotdId)!!
        else Algorithms.getOll(aotdId)!!


        binding.aotdName.text = if (aotdType.equals("pll")) alg.type + " perm"
        else aotdId.toString() + " oll"

        if (alg.alg.startsWith("y")) {
            val int = when (alg.alg[1]) {
                '\'' -> -1
                '2' -> 2
                else -> 1
            }

            binding.algorithmText.text = alg.alg.substring(alg.alg.indexOf(" "))
            binding.cubeOrientation.rotation = (int * 90).toFloat()

        } else {
            binding.algorithmText.text = alg.alg
            binding.cubeOrientation.rotation = 0F
        }

        // TODO: fix micro movements of cubeOrientation might be because of rotations (when not quickly switching
        //  pictures it is not noticible

        binding.cubeOrientation.setImageDrawable(CubeDrawable(alg.layer))
    }

    private fun update() {
        aotdId = aotdId + 1
        if (aotdType.equals("pll") && aotdId >= 22) {
            aotdType = "oll"
            aotdId = 1
        } else if (aotdType.equals("oll") && aotdId >= 58) {
            aotdType = "pll"
            aotdId = 1
        }
    }
}