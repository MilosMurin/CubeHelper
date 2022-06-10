package me.milos.murin.cubehelper.data

import android.graphics.Paint

class LastLayer(initList: List<Int>) {

    private val top: Array<IntArray> = filledTop(0)
    private val sides: Array<IntArray> = filledSides(0)

    init {
        for (i in 0..8) {
            top[i % 3][i / 3] = initList[i]
        }
        for (i in 9..20) {
            sides[i / 3 - 3][i % 3] = initList[i]
        }
    }

    fun getTop(row: Int, column: Int): Paint {
        return getPaint(top[row][column])
    }

    fun getCi(index: Int): Paint {
        return getPaint(sides[index][0])
    }

    fun getCiP2(index: Int): Paint {
        return getPaint(sides[index][2])
    }

    fun getMV(index: Int): Paint {
        return getPaint(when (index) {
                            0 -> sides[0][1]
                            else -> sides[2][1]
                        })
    }

    fun getMH(index: Int): Paint {
        return getPaint(when (index) {
                            0 -> sides[3][1]
                            else -> sides[1][1]
                        })
    }


    companion object {
        private val defaultPaint: Paint = Paint().apply { setARGB(255, 43, 42, 42) } // 0
        private val red: Paint = Paint().apply { setARGB(255, 255, 0, 0) } // 1
        private val orange: Paint = Paint().apply { setARGB(255, 255, 165, 0) } // 2
        private val white: Paint = Paint().apply { setARGB(255, 255, 255, 255) } // 3
        private val yellow: Paint = Paint().apply { setARGB(255, 255, 255, 0) } // 4
        private val green: Paint = Paint().apply { setARGB(255, 0, 255, 0) } // 5
        private val blue: Paint = Paint().apply { setARGB(255, 0, 0, 255) } // 6


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

        fun filledTop(c: Int): Array<IntArray> {
            return arrayOf(intArrayOf(c, c, c), intArrayOf(c, c, c), intArrayOf(c, c, c))
        }

        fun filledSides(c: Int): Array<IntArray> { // T, R, B, L
            return arrayOf(intArrayOf(c, c, c), intArrayOf(c, c, c), intArrayOf(c, c, c), intArrayOf(c, c, c))
        }

    }

}