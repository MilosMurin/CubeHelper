package me.milos.murin.cubehelper.data

class Algorithms {

    data class Algorithm(val type: String, val alg: String, val layer: LastLayer)


    companion object {

        private val pll: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("Aa", "l' U R' D2 R U' R' D2 R2", makePll(1,5,1,6,1,2,5,6,6,2,2,5)),
            2 to Algorithm("Ab", "x R2 D2 R U R' D2 R U' R x'", makePll(6,5,2,5,1,5,1,6,6,2,2,1)),
            3 to Algorithm("E", "y x' R U' R' D R U R' D' R U R' D R U' R' D' x", makePll(1,5,2,5,1,6,2,6,1,6,2,5)),
            // y
            4 to Algorithm("F", "y R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R", makePll(6,1,5,1,5,6,2,2,2,5,6,1)),
            // y
            5 to Algorithm("Ga", "R2 U R' U R' U' R U' R2 D U' R' U R D'", makePll(5,6,1,6,2,5,1,1,6,2,5,2)),
            6 to Algorithm("Gb", "R' U' R U D' R2 U R' U R U' R U' R2 D", makePll(5,2,1,6,6,5,1,5,6,2,1,2)),
            7 to Algorithm("Gc", "R2 U' R U' R U R' U R2 D' U R U' R' D", makePll(5,1,1,6,2,5,1,5,6,2,6,2)),
            8 to Algorithm("Gd", "R U R' U' D R2 U' R U' R' U R' U R2 D'", makePll(5,6,1,6,5,5,1,2,6,2,1,2)),
            9 to Algorithm("H", "M2 U M2 U2 M2 U M2", makePll(5,6,5,1,2,1,6,5,6,2,1,2)),
            10 to Algorithm("Ja", "y R' U L' U2 R U' R' U2 R L", makePll(2,5,5,1,1,1,6,2,2,5,6,6)), // y
            11 to Algorithm("Jb", "R U R' F' R U R' U' R' F R2 U' R'", makePll(5,5,1,6,6,5,1,1,6,2,2,2)),
            12 to Algorithm("Na", "R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'", makePll(1,1,2,5,5,6,2,2,1,
                                                                                                   6,6,5)),
            13 to Algorithm("Nb", "R' U R U' R' F' U' F R U R' F R' F' R U' R", makePll(1,2,2,5,6,6,2,1,1,6,5,5)),
            14 to Algorithm("Ra", "y R U R' F' R U2 R' U2 R' F R U R U2 R'", makePll(5,2,6,2,5,5,1,6,1,6,1,2)), // y
            15 to Algorithm("Rb", "R' U2 R U2 R' F R U R' U' R' F' R2", makePll(6,1,5,1,2,6,2,6,2,5,5,1)),
            16 to Algorithm("T", "R U R' U' R' F R2 U' R' U' R U R' F'", makePll(5,5,1,6,2,5,1,6,6,2,1,2)),
            17 to Algorithm("Ua", "y2 R U' R U R U R U' R' U' R2", makePll(5,2,5,1,5,1,6,6,6,2,1,2)), // y2
            18 to Algorithm("Ub", "y2 R2 U R U R' U' R' U' R' U R'", makePll(5,1,5,1,2,1,6,6,6,2,5,2)), // y2
            19 to Algorithm("V", "R' U R' d' R' F' R2 U' R' U R' F R F", makePll(2,6,1,6,1,5,1,2,2,5,5,6)),
            20 to Algorithm("Y", "F R U' R' U' R U R' F' R U R' U' R' F R F'", makePll(6,2,5,1,1,2,5,6,6,2,5,1)),
            21 to Algorithm("Z", "M' U' M2 U' M2 U' M' U2 M2", makePll(6,1,6,2,5,2,5,2,5,1,6,1))
        )

        fun getPll(name: Int): Algorithm? {
            return pll[name]
        }

        fun getRandomPll(): Int {
            return pll.keys.random()
        }

        private fun makePll(vararg indexes: Int): LastLayer {

            val list = mutableListOf(4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            var counter = 9
            for (i in indexes) {
                list[counter] = i
                counter++
            }

            return LastLayer(list)

        }

        private val oll: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("Dot", "R U2 R2 F R F' U2 R' F R F'", makeOll(4,10,12,13,14,16,18,19,20)),
            2 to Algorithm("Dot", "F R U R' U' F' f R U R' U' f'", makeOll(4, 10,11,13,15,16,18,19,20)),
            3 to Algorithm("Dot", "y' f R U R' U' f' U' F R U R' U' F'", makeOll(4,6,9,10,12,13,15,16,19)), // y
            4 to Algorithm("Dot", "y' f R U R' U' f' U F R U R' U' F'", makeOll(4,8,10,11,13,16,17,19,20)), // y
            5 to Algorithm("Square", "r' U2 R U R' U r", makeOll(4,5,7,8,9,10,12,18,19)),
            6 to Algorithm("Square", "r U2 R' U' R U' r'", makeOll(1,2,4,5,14,16,17,19,20)),
            7 to Algorithm("Small lightning", "r U R' U R U2 r'", makeOll(1,3,4,6,9,12,13,15,16)),
            8 to Algorithm("Small lightning", "y2 r' U' R U' R' U2 r", makeOll(1,4,5,8,11,16,17,19,20)), // y2
            9 to Algorithm("Fish", "y R U R' U' R' F R2 U R' U' F'", makeOll(2,3,4,7,10,13,14,17,20)), // y
            10 to Algorithm("Fish", "R U R' U R' F R F' R U2 R'", makeOll(2,3,4,7,9,10,13,15,18)),
            11 to Algorithm("Small lightning", "r' R2 U R' U R U2 R' U M'", makeOll(4,5,6,7,9,10,12,15,19)),
            12 to Algorithm("Small lightning", "F R U R' U' F' U F R U R' U' F'", makeOll(2,4,5,7,10,14,17,19,20)),
            13 to Algorithm("Knight move", "r U' r' U' r U r' F' U F", makeOll(3,4,5,6,9,10,12,15,16)),
            14 to Algorithm("Knight move", "R' F R U R' F' R F U' F'", makeOll(3,4,5,8,10,11,15,16,20)),
            15 to Algorithm("Knight move", "r' U' r R' U' R U r' U r", makeOll(3,4,5,8,9,10,12,16,18)),
            16 to Algorithm("Knight move", "r U r' R U R' U' r U' r'", makeOll(2,3,4,5,10,14,16,17,20)),
            17 to Algorithm("Dot", "R U R' U R' F R F' U2 R' F R F'", makeOll(0,4,8,10,11,13,16,18,19)),
            18 to Algorithm("Dot", "r U R' U R U2 r2 U' R U' R' U2 r", makeOll(0,2,4,10,13,15,16,17,19)),
            19 to Algorithm("Dot", "M U R U R' U' M' R' F R F'", makeOll(0,2,4,10,13,14,16,18,19)),
            20 to Algorithm("Dot", "M U R U R' U' M2 U R U' r'", makeOll(0,2,4,6,8,10,13,16,19)),
            21 to Algorithm("Cross", "y R U2 R' U' R U R' U' R U' R'", makeOll(1,3,4,5,7,12,14,18,20)), // y
            22 to Algorithm("Cross", "R U2 R2 U' R2 U' R2 U2 R", makeOll(1,3,4,5,7,11,15,18,20)),
            23 to Algorithm("Cross", "R2 D R' U2 R D' R' U2 R'", makeOll(0,1,2,3,4,5,7,15,17)),
            24 to Algorithm("Cross", "r U R' U' r' F R F'", makeOll(1,2,3,4,5,7,8,9,17)),
            25 to Algorithm("Cross", "y F' r U R' U' r' F R", makeOll(0,1,3,4,5,7,8,12,17)), // y
            26 to Algorithm("Cross", "y R U2 R' U' R U' R'", makeOll(0,1,3,4,5,7,11,14,17)), // y
            27 to Algorithm("Cross", "R U R' U R U2 R'", makeOll(1,3,4,5,6,7,9,12,15)),
            28 to Algorithm("Corners oriented", "r U R' U' M U R U' R'", makeOll(0,1,2,3,4,6,8,13,16)),
            29 to Algorithm("Awkward shape", "M U R U R' U' R' F R F' M'", makeOll(0,2,3,4,7,10,13,14,18)),
            30 to Algorithm("Awkward shape", "y2 F U R U2 R' U' R U2 R' U' F'", makeOll(0,2,4,5,7,10,14,18,19)), // y2
            31 to Algorithm("P shape", "R' U' F U R U' R' F' R", makeOll(1,2,4,5,8,9,16,17,19)),
            32 to Algorithm("P shape", "S R U R' U' R' F R f'", makeOll(2,4,5,7,8,9,10,17,19)),
            33 to Algorithm("T shape", "R U R' U' R' F R F'", makeOll(2,3,4,5,8,9,10,17,16)),
            34 to Algorithm("C shape", "y2 R U R2 U' R' F R U R U' F'", makeOll(0,2,3,4,5,10,14,16,18)), // y2
            35 to Algorithm("Fish", "R U2 R2 F R F' R U2 R'", makeOll(0,4,5,7,8,10,12,17,19)),
            36 to Algorithm("W shape", "y2 L' U' L U' L' U L U L F' L' F", makeOll(0,3,4,7,8,10,12,13,17)),
            37 to Algorithm("Fish", "F R U' R' U' R U R' F'", makeOll(0,1,3,4,8,12,13,16,17)),
            38 to Algorithm("W shape", "R U R' U R U' R' U' R' F R F'", makeOll(1,2,3,4,6,9,13,14,16)),
            39 to Algorithm("Big lightning", "y L F' L' U' L U F U' L'", makeOll(0,1,4,7,8,11,13,18,19)),
            40 to Algorithm("Big lightning", "y R' F R U R' U' F' U R", makeOll(1,2,4,6,7,13,15,19,20)),
            41 to Algorithm("Awkward shape", "y2 R U R' U R U2 R' F R U R' U' F'", makeOll(0,2,4,5,7,10,15,17,19)),
            // y2
            42 to Algorithm("Awkward shape", "R' U' R U' R' U2 R F R U R' U' F'", makeOll(0,2,3,4,7,10,13,15,17)),
            43 to Algorithm("P shape", "f' L' U' L U f", makeOll(0,3,4,6,7,10,12,13,14)),
            44 to Algorithm("P shape", "f R U R' U' f'", makeOll(2,4,5,7,8,10,18,19,20)),
            45 to Algorithm("T shape", "F R U R' U' F'", makeOll(2,3,4,5,8,10,16,18,20)),
            46 to Algorithm("C shape", "R' U' R' F R F' U R", makeOll(0,1,4,6,7,12,13,14,19)),
            47 to Algorithm("Small L", "F' L' U' L U L' U' L U F", makeOll(1,4,5,9,12,14,16,17,19)),
            48 to Algorithm("Small L", "F R U R' U' R U R' U' F'", makeOll(1,3,4,11,13,15,16,18,20)),
            49 to Algorithm("Small L", "y2 r U' r2 U r2 U r2 U' r", makeOll(3,4,7,9,10,12,13,14,17)), // y2
            50 to Algorithm("Small L", "r' U r2 U' r2 U' r2 U r'", makeOll(4,5,7,10,11,15,18,19,20)),
            51 to Algorithm("I shape", "f R U R' U' R U R' U' f'", makeOll(3,4,5,10,11,15,16,18,20)),
            52 to Algorithm("I shape", "R U R' U R d' R U' R' F'", makeOll(1,4,7,9,12,13,14,17,19)),
            53 to Algorithm("Small L", "r' U' R U' R' U R U' R' U2 r", makeOll(4,5,7,10,12,14,18,19,20)),
            54 to Algorithm("Small L", "r U R' U R U' R' U R U2 r'", makeOll(1,4,5,12,14,16,18,19,20)),
            55 to Algorithm("I shape", "R U2 R2 U' R U' R' U2 F R F'", makeOll(1,4,7,12,13,14,18,19,20)),
            56 to Algorithm("I shape", "r U r' U R U' R' U R U' R' r U' r'", makeOll(3,4,5,10,12,14,16,18,20)),
            57 to Algorithm("Corners oriented", "R U R' U' M' U R U' r'", makeOll(0,2,3,4,5,6,8,10,16))
        )

        fun getOll(index: Int): Algorithm? {
            return oll[index]
        }

        fun getRandomOll(): Int {
            return oll.keys.random()
        }

        private fun makeOll(vararg indexes: Int): LastLayer {

            val list = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            for (i in indexes) {
                list[i] = 4
            }

            return LastLayer(list)
        }
    }

}