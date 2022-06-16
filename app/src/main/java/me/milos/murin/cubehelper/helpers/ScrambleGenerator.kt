package me.milos.murin.cubehelper.helpers

import kotlin.random.Random

/**
 * Trieda generujuca nahodne rozlozenia kocky
 */
class ScrambleGenerator {
    companion object {
        /**
         * Mozne pohyby pri rozkladani
         */
        private val moves = listOf("R", "L", "U", "D", "F", "B")
        /**
         * Mozne upravovatele pohybov
         */
        private val modifiers = listOf("'", "2", "")

        /**
         * Vygeneruje nahodne rozlozenie kocky
         */
        fun generateScramble(): String {
            val builder = java.lang.StringBuilder()
            var prevLastIndex = -1
            var lastIndex = -1

            for (i in 1..Random.nextInt(20, 25)) {
                // vyberiem nahodny pohyb
                var index = Random.nextInt(moves.size)
                // testujem ci som nahodou nevytiahol nezmyselny pohyb -> napr R ked som hned pred vytiahol R
                while (!isValid(index, lastIndex, prevLastIndex)) {
                    index = Random.nextInt(moves.size)
                }
                prevLastIndex = lastIndex
                lastIndex = index
                // pridam do rozlozenia
                builder.append(moves[index])
                builder.append(modifiers[Random.nextInt(modifiers.size)])
                builder.append(" ")
            }
            return builder.toString()
        }

        /**
         * Metoda, ktora testuje, ci som nevybral nezmyselny pohyb
         * Nezmyselny pohybe je vtedy ak som:
         *  1) vytiahol dva rovnake za sebou
         *  2) vytihol dva rovnake a medzi nimi je opacny k nim (napr.: L R L) (opacne: L<->R, U<->D, F<->B)
         */
        private fun isValid(index: Int, lastIndex: Int, prevLastIndex: Int): Boolean {
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