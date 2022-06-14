package me.milos.murin.cubehelper.layouts.algOfTheDay

import androidx.lifecycle.ViewModel
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R
import me.milos.murin.cubehelper.data.Algorithms
import me.milos.murin.cubehelper.helpers.CubeDrawable

class AOTDViewModel : ViewModel() {

    // for saving
    private lateinit var type: String

    private var id: Int = 1

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
        setRandomAlg()
    }

    private fun setRandomAlg() {
        Algorithms.selectRandomAlg()

        type = Algorithms.getType()
        id = Algorithms.getId()
        setAlgorithm(type, id)
    }


    private fun setAlgorithm(type: String, id: Int) {
        alg = Algorithms.getAlg(type, id)!!

        _algName = Algorithms.algName(type, id)

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