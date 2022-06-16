package me.milos.murin.cubehelper.data

import android.graphics.Paint
import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R

/**
 * Trieda reprezentujuca pociatocny stav algoritmu
 * uklada info o tom ako su zafarbene policka v pociatocnom stave
 *
 * kocka ma indexy
 *
 * vrch kocky:
 *  ______
 * |0|1|2|
 *  ______
 * |3|4|5|
 *  ______
 * |6|7|8|
 * ______
 *
 * boky kocky:
 * idem v smere hodinovych ruciciek, zacnem nad polickom 0 a indexom 9
 */
class LastLayer(initList: List<Int>) {

    /**
     * zafarbene policok na vrchu kocky v pociatocnom stave
     *
     * ulozene to je v dvojrozmernom poli podla riadkov ako su na kocke
     */
    private val top: Array<IntArray> = filledTop(0)

    /**
     * zafarbene policok po boku kocky v pociatocnom stave
     */
    private val sides: Array<IntArray> = filledSides(0)

    init {
        for (i in 0..8) {
            top[i % 3][i / 3] = initList[i]
        }
        for (i in 9..20) {
            sides[i / 3 - 3][i % 3] = initList[i]
        }
    }

    /**
     * Vrati zafarbenie policka na vrchu kocky s danymi suradnicami
     */
    fun getTop(row: Int, column: Int): Paint {
        return getPaint(top[row][column])
    }

    /**
     * Vrati zafarbenie policka na boku kocky v lavom rohu
     */
    fun getCornerI(index: Int): Paint {
        return getPaint(sides[index][0])
    }

    /**
     * Vrati zafarbenie policka na boku kocky v pravom rohu
     */
    fun getCornerIPlusTwo(index: Int): Paint {
        return getPaint(sides[index][2])
    }

    /**
     * Vrati zafarbenie policka na boku kocky vo vertikalne polozenom strede
     */
    fun getMidVertical(index: Int): Paint {
        return getPaint(when (index) {
                            0 -> sides[0][1]
                            else -> sides[2][1]
                        })
    }

    /**
     * Vrati zafarbenie policka na boku kocky vo horizontalne polozenom strede
     */
    fun getMidHorizontal(index: Int): Paint {
        return getPaint(when (index) {
                            0 -> sides[3][1]
                            else -> sides[1][1]
                        })
    }


    companion object {
        // zakladne farby kocky
        private val defaultPaint: Paint = App.getPaint(R.color.cubeDefault)
        private val red: Paint = Paint().apply { setARGB(255, 255, 0, 0) } // 1
        private val orange: Paint = Paint().apply { setARGB(255, 255, 165, 0) } // 2
        private val white: Paint = Paint().apply { setARGB(255, 255, 255, 255) } // 3
        private val yellow: Paint = Paint().apply { setARGB(255, 255, 255, 0) } // 4
        private val green: Paint = Paint().apply { setARGB(255, 0, 255, 0) } // 5
        private val blue: Paint = Paint().apply { setARGB(255, 0, 0, 255) } // 6

        /**
         * Vrati farbu podla indexu farby
         */
        fun getPaint(index: Int): Paint {
            return when (index) {
                1 -> red
                2 -> orange
                3 -> white
                4 -> yellow
                5 -> green
                6 -> blue
                else -> defaultPaint
            }
        }

        /**
         * varti vyplneny vrch kocky s danom farbou
         */
        fun filledTop(color: Int): Array<IntArray> {
            return arrayOf(intArrayOf(color, color, color), intArrayOf(color, color, color), intArrayOf(color, color, color))
        }

        /**
         * varti vyplneny bok kocky s danom farbou
         */
        fun filledSides(color: Int): Array<IntArray> { // T, R, B, L
            return arrayOf(intArrayOf(color, color, color), intArrayOf(color, color, color), intArrayOf(color, color, color), intArrayOf(color, color, color))
        }

    }

}