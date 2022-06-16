package me.milos.murin.cubehelper.layouts.algOfTheDay

import androidx.lifecycle.ViewModel
import me.milos.murin.cubehelper.data.Algorithms
import me.milos.murin.cubehelper.helpers.CubeDrawable

/**
 * View model obsahujuci data o algoritme dna
 * @property alg algortimus dna
 * @property _algText kroky algoritmu v retazci
 * @property _algName nazov algoritmu
 * @property _rotation kolkokrat treba kocku otocit o 90 stupnov
 * @property _cubeDrawable kocka na zobrazenie
 */
class AOTDViewModel : ViewModel() {

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
        // Nastavi algoritmus na vybraty algoritmus
        Algorithms.selectRandomAlg()

        updateAlgorithm()
    }

    /**
     * Updatne informacie o algoritme
     */
    fun updateAlgorithm() {
        alg = Algorithms.getAlg()!!

        _algName = Algorithms.algName()

        val realAlg = Algorithms.getEdited() ?: alg.alg

        if (realAlg.startsWith("y")) {
            _rotation = when (realAlg[1]) {
                '\'' -> -1
                '2' -> 2
                else -> 1
            }
            _algText = realAlg.substring(realAlg.indexOf(" "))

        } else {
            _rotation = 0
            _algText = realAlg
        }
        _cubeDrawable = CubeDrawable(alg.layer)
    }
}