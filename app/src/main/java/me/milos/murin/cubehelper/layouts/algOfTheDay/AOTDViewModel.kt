package me.milos.murin.cubehelper.layouts.algOfTheDay

import androidx.lifecycle.ViewModel
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.data.Algorithms
import me.milos.murin.cubehelper.helpers.CubeDrawable

class AOTDViewModel: ViewModel() {

    private lateinit var alg: Algorithms.Algorithm

    private lateinit var _algText: String
    val algText: String
        get() = _algText

    private lateinit var _algName: String
    val algName: String
        get() = _algName

    private var _rotation: Int = 0
    val rotation: Float
        get() = (_rotation * 90).toFloat()

    private lateinit var _cubeDrawable: CubeDrawable
    val cubeDrawable: CubeDrawable
        get() = _cubeDrawable

    init {
        setRandomAlg() // TODO: Only reset on/after midnight
    }


    private fun setRandomAlg() {
        val aotdType = listOf("pll", "oll").random()
        setAlgorithm(aotdType, if (aotdType == "pll") Algorithms.getRandomPll()
                                else Algorithms.getRandomOll())
    }


    private fun setAlgorithm(type: String, id: Int) {
        alg = if (type == "pll") Algorithms.getPll(id)!!
            else Algorithms.getOll(id)!!

        _algName = if (type == "pll") App.get(R.string.permutationShort, alg.type)
            else App.get(R.string.oll, id)

        if (alg.alg.startsWith("y")) {
            _rotation = when (alg.alg[1]) {
                '\'' -> -1
                '2' -> 2
                else -> 1
            }
            _algText = alg.alg.substring(alg.alg.indexOf(" "))

        } else {
            _rotation = 0
            _algText = alg.alg
        }
        _cubeDrawable = CubeDrawable(alg.layer)
    }

}