package me.milos.murin.cubehelper.helpers

import kotlin.random.Random

class ScrambleGenerator {
    companion object {

        private val moves = listOf("R", "L", "U", "D", "F", "B")
        private val modifiers = listOf("'", "2", "")

        fun generateScramble(): String {
            val builder = java.lang.StringBuilder()
            var prevLastIndex = -1
            var lastIndex = -1

            for (i in 1..Random.nextInt(20, 25)) {
                var index = Random.nextInt(moves.size)
                while (!isValid(index, lastIndex, prevLastIndex)) {
                    index = Random.nextInt(moves.size)
                }
                prevLastIndex = lastIndex
                lastIndex = index
                builder.append(moves[index])
                builder.append(modifiers[Random.nextInt(modifiers.size)])
                builder.append(" ")
            }
            return builder.toString()
        }

        fun isValid(index: Int, lastIndex: Int, prevLastIndex: Int): Boolean {
            if (index % 2 == 0) {
                if (lastIndex == index) { // ak som vytiahol rovnake pismeno tak je zle
                    return false
                } else if (lastIndex == index + 1) { // ak som vytiahol pismeno oproti tak je mozno zle
                    if (prevLastIndex == index) {
                        return false
                    }
                }
            } else if (index % 2 == 1) {
                if (lastIndex == index) { // ak som vytiahol rovnake pismeno tak je zle
                    return false
                } else if (lastIndex == index - 1) { // ak som vytiahol pismeno oproti tak je mozno zle
                    if (prevLastIndex == index) {
                        return false
                    }
                }
            }
            return true
        }
    }
}