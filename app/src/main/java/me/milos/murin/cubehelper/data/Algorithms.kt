package me.milos.murin.cubehelper.data

import me.milos.murin.cubehelper.App
import me.milos.murin.cubehelper.R

class Algorithms {

    data class Algorithm(val type: String, val alg: String, val layer: LastLayer)


    companion object {

        fun getRandomType(): String {
            return listOf("pll", "oll", "coll", "wv").random()
        }
        fun getRandomId(type: String): Int {
            return when (type) {
                "oll" -> oll.keys.random()
                "coll" -> coll.keys.random()
                "wv" -> wv.keys.random()
                else -> pll.keys.random()
            }
        }

        fun getAlg(type: String, id: Int): Algorithm? {
            return when(type) {
                "oll" -> oll.get(id)
                "coll" -> coll.get(id)
                "wv" -> wv.get(id)
                else -> pll.get(id)
            }
        }

        fun algName(type: String, id: Int): String {
            return when(type) {
                "oll" -> App.get(R.string.oll, id)
                "coll" -> App.get(R.string.coll, coll.get(id)!!.type)
                "wv" -> App.get(R.string.wv, wv.get(id)!!.type)
                else -> App.get(R.string.permutationShort, pll.get(id)!!.type)
            }
        }

        // permute last layer
        private val pll: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("Aa", "l' U R' D2 R U' R' D2 R2", makePll(1,5,1,6,1,2,5,6,6,2,2,5)),
            2 to Algorithm("Ab", "x R2 D2 R U R' D2 R U' R x'", makePll(6,5,2,5,1,5,1,6,6,2,2,1)),
            3 to Algorithm("E", "y x' R U' R' D R U R' D' R U R' D R U' R' D' x", makePll(1,5,2,5,1,6,2,6,1,6,2,5)),
            4 to Algorithm("F", "y R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R", makePll(6,1,5,1,5,6,2,2,2,5,6,1)),
            5 to Algorithm("Ga", "R2 U R' U R' U' R U' R2 D U' R' U R D'", makePll(5,6,1,6,2,5,1,1,6,2,5,2)),
            6 to Algorithm("Gb", "R' U' R U D' R2 U R' U R U' R U' R2 D", makePll(5,2,1,6,6,5,1,5,6,2,1,2)),
            7 to Algorithm("Gc", "R2 U' R U' R U R' U R2 D' U R U' R' D", makePll(5,1,1,6,2,5,1,5,6,2,6,2)),
            8 to Algorithm("Gd", "R U R' U' D R2 U' R U' R' U R' U R2 D'", makePll(5,6,1,6,5,5,1,2,6,2,1,2)),
            9 to Algorithm("H", "M2 U M2 U2 M2 U M2", makePll(5,6,5,1,2,1,6,5,6,2,1,2)),
            10 to Algorithm("Ja", "y R' U L' U2 R U' R' U2 R L", makePll(2,5,5,1,1,1,6,2,2,5,6,6)),
            11 to Algorithm("Jb", "R U R' F' R U R' U' R' F R2 U' R'", makePll(5,5,1,6,6,5,1,1,6,2,2,2)),
            12 to Algorithm("Na", "R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'", makePll(1,1,2,5,5,6,2,2,1,
                                                                                                   6,6,5)),
            13 to Algorithm("Nb", "R' U R U' R' F' U' F R U R' F R' F' R U' R", makePll(1,2,2,5,6,6,2,1,1,6,5,5)),
            14 to Algorithm("Ra", "y R U R' F' R U2 R' U2 R' F R U R U2 R'", makePll(5,2,6,2,5,5,1,6,1,6,1,2)),
            15 to Algorithm("Rb", "R' U2 R U2 R' F R U R' U' R' F' R2", makePll(6,1,5,1,2,6,2,6,2,5,5,1)),
            16 to Algorithm("T", "R U R' U' R' F R2 U' R' U' R U R' F'", makePll(5,5,1,6,2,5,1,6,6,2,1,2)),
            17 to Algorithm("Ua", "y2 R U' R U R U R U' R' U' R2", makePll(5,2,5,1,5,1,6,6,6,2,1,2)),
            18 to Algorithm("Ub", "y2 R2 U R U R' U' R' U' R' U R'", makePll(5,1,5,1,2,1,6,6,6,2,5,2)),
            19 to Algorithm("V", "R' U R' d' R' F' R2 U' R' U R' F R F", makePll(2,6,1,6,1,5,1,2,2,5,5,6)),
            20 to Algorithm("Y", "F R U' R' U' R U R' F' R U R' U' R' F R F'", makePll(6,2,5,1,1,2,5,6,6,2,5,1)),
            21 to Algorithm("Z", "M' U' M2 U' M2 U' M' U2 M2", makePll(6,1,6,2,5,2,5,2,5,1,6,1))
        )

        fun getPll(name: Int): Algorithm? {
            return pll[name]
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

        // orientate last layer
        private val oll: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("Dot", "R U2 R2 F R F' U2 R' F R F'", makeOll(4,10,12,13,14,16,18,19,20)),
            2 to Algorithm("Dot", "F R U R' U' F' f R U R' U' f'", makeOll(4, 10,11,13,15,16,18,19,20)),
            3 to Algorithm("Dot", "y' f R U R' U' f' U' F R U R' U' F'", makeOll(4,6,9,10,12,13,15,16,19)),
            4 to Algorithm("Dot", "y' f R U R' U' f' U F R U R' U' F'", makeOll(4,8,10,11,13,16,17,19,20)),
            5 to Algorithm("Square", "r' U2 R U R' U r", makeOll(4,5,7,8,9,10,12,18,19)),
            6 to Algorithm("Square", "r U2 R' U' R U' r'", makeOll(1,2,4,5,14,16,17,19,20)),
            7 to Algorithm("Small lightning", "r U R' U R U2 r'", makeOll(1,3,4,6,9,12,13,15,16)),
            8 to Algorithm("Small lightning", "y2 r' U' R U' R' U2 r", makeOll(1,4,5,8,11,16,17,19,20)),
            9 to Algorithm("Fish", "y R U R' U' R' F R2 U R' U' F'", makeOll(2,3,4,7,10,13,14,17,20)),
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
            21 to Algorithm("Cross", "y R U2 R' U' R U R' U' R U' R'", makeOll(1,3,4,5,7,12,14,18,20)),
            22 to Algorithm("Cross", "R U2 R2 U' R2 U' R2 U2 R", makeOll(1,3,4,5,7,11,15,18,20)),
            23 to Algorithm("Cross", "R2 D R' U2 R D' R' U2 R'", makeOll(0,1,2,3,4,5,7,15,17)),
            24 to Algorithm("Cross", "r U R' U' r' F R F'", makeOll(1,2,3,4,5,7,8,9,17)),
            25 to Algorithm("Cross", "y F' r U R' U' r' F R", makeOll(0,1,3,4,5,7,8,12,17)),
            26 to Algorithm("Cross", "y R U2 R' U' R U' R'", makeOll(0,1,3,4,5,7,11,14,17)),
            27 to Algorithm("Cross", "R U R' U R U2 R'", makeOll(1,3,4,5,6,7,9,12,15)),
            28 to Algorithm("Corners oriented", "r U R' U' M U R U' R'", makeOll(0,1,2,3,4,6,8,13,16)),
            29 to Algorithm("Awkward shape", "M U R U R' U' R' F R F' M'", makeOll(0,2,3,4,7,10,13,14,18)),
            30 to Algorithm("Awkward shape", "y2 F U R U2 R' U' R U2 R' U' F'", makeOll(0,2,4,5,7,10,14,18,19)),
            31 to Algorithm("P shape", "R' U' F U R U' R' F' R", makeOll(1,2,4,5,8,9,16,17,19)),
            32 to Algorithm("P shape", "S R U R' U' R' F R f'", makeOll(2,4,5,7,8,9,10,17,19)),
            33 to Algorithm("T shape", "R U R' U' R' F R F'", makeOll(2,3,4,5,8,9,10,17,16)),
            34 to Algorithm("C shape", "y2 R U R2 U' R' F R U R U' F'", makeOll(0,2,3,4,5,10,14,16,18)),
            35 to Algorithm("Fish", "R U2 R2 F R F' R U2 R'", makeOll(0,4,5,7,8,10,12,17,19)),
            36 to Algorithm("W shape", "y2 L' U' L U' L' U L U L F' L' F", makeOll(0,3,4,7,8,10,12,13,17)),
            37 to Algorithm("Fish", "F R U' R' U' R U R' F'", makeOll(0,1,3,4,8,12,13,16,17)),
            38 to Algorithm("W shape", "R U R' U R U' R' U' R' F R F'", makeOll(1,2,3,4,6,9,13,14,16)),
            39 to Algorithm("Big lightning", "y L F' L' U' L U F U' L'", makeOll(0,1,4,7,8,11,13,18,19)),
            40 to Algorithm("Big lightning", "y R' F R U R' U' F' U R", makeOll(1,2,4,6,7,13,15,19,20)),
            41 to Algorithm("Awkward shape", "y2 R U R' U R U2 R' F R U R' U' F'", makeOll(0,2,4,5,7,10,15,17,19)),
            42 to Algorithm("Awkward shape", "R' U' R U' R' U2 R F R U R' U' F'", makeOll(0,2,3,4,7,10,13,15,17)),
            43 to Algorithm("P shape", "f' L' U' L U f", makeOll(0,3,4,6,7,10,12,13,14)),
            44 to Algorithm("P shape", "f R U R' U' f'", makeOll(2,4,5,7,8,10,18,19,20)),
            45 to Algorithm("T shape", "F R U R' U' F'", makeOll(2,3,4,5,8,10,16,18,20)),
            46 to Algorithm("C shape", "R' U' R' F R F' U R", makeOll(0,1,4,6,7,12,13,14,19)),
            47 to Algorithm("Small L", "F' L' U' L U L' U' L U F", makeOll(1,4,5,9,12,14,16,17,19)),
            48 to Algorithm("Small L", "F R U R' U' R U R' U' F'", makeOll(1,3,4,11,13,15,16,18,20)),
            49 to Algorithm("Small L", "y2 r U' r2 U r2 U r2 U' r", makeOll(3,4,7,9,10,12,13,14,17)),
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

        private fun makeOll(vararg indexes: Int): LastLayer {

            val list = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            for (i in indexes) {
                list[i] = 4
            }

            return LastLayer(list)
        }

        // corners of last layer
        private val collYellows: HashMap<Char, IntArray> = hashMapOf(
            'H' to intArrayOf(12,14,18,20),
            'L' to intArrayOf(2,6,15,20),
            'P' to intArrayOf(11,15,18,20),
            'T' to intArrayOf(6,8,12,20),
            'U' to intArrayOf(6,8,9,11)
        )

        private val collFills: HashMap<Char, IntArray> = hashMapOf(
            'H' to intArrayOf(0,2,6,8,9,11,15,17),
            'L' to intArrayOf(0,8,9,11,12,14,17,18),
            'P' to intArrayOf(0,2,6,8,9,12,14,17),
            'T' to intArrayOf(0,2,9,11,14,15,17,18),
            'U' to intArrayOf(0,2,12,14,15,17,18,20)
        )

        private val coll: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("H1","R U R' U R U' R' U R U2 R'", makeCOll('H',5,5,6,6,2,1,1,2)),
            2 to Algorithm("H2","F R U' R' U R U2 R' U' R U R' U' F'", makeCOll('H',2,1,5,5,6,6,2,1)),
            3 to Algorithm("H3","R U R' U R U L' U R' U' L", makeCOll('H',5,1,6,1,2,6,5,2)),
            4 to Algorithm("H4","y F R U R' U' R U R' U' R U R' U' F'", makeCOll('H',5,6,5,6,2,2,1,1)),
            5 to Algorithm("L1","R' U2 R U R' U' R U R' U' R U R' U R", makeCOll('L',6,2,1,6,2,5,5,1)),
            6 to Algorithm("L2","y2 R' U2 R' D' R U2 R' D R2", makeCOll('L',5,5,2,6,2,1,1,6)),
            7 to Algorithm("L3","y' R U2 R D R' U2 R D' R2", makeCOll('L',6,6,1,2,5,2,5,1)),
            8 to Algorithm("L4","y' F R' F' r U R U' r'", makeCOll('L',5,6,2,5,1,2,1,6)),
            9 to Algorithm("L5","y' x R' U R D' R' U' R D", makeCOll('L',1,2,5,6,2,5,1,6)),
            10 to Algorithm("L6","y R' U' R U R' F' R U R' U' R' F R2", makeCOll('L',5,1,2,6,2,6,5,1)),
            11 to Algorithm("P1","R U2 R2 U' R2 U' R2 U2 R", makeCOll('P',6,2,5,2,1,6,5,1)),
            12 to Algorithm("P2","R' F2 R U2 R U2 R' F2 U' R U' R'", makeCOll('P',6,1,2,6,1,5,2,5)),
            13 to Algorithm("P3","R' U' F' R U R' U' R' F R2 U2 R' U2 R", makeCOll('P',5,6,6,5,2,1,1,2)),
            14 to Algorithm("P4","R U R' U' R' F R2 U R' U' R U R' U' F'", makeCOll('P',2,5,5,1,6,2,6,1)),
            15 to Algorithm("P5","R U' L' U R' U L U L' U L", makeCOll('P',2,1,2,1,6,5,6,5)),
            16 to Algorithm("P6","R2 D' R U R' D R U R U' R' U R U R' U R", makeCOll('P',1,5,1,6,5,2,2,6)),
            17 to Algorithm("T1","R U2 R' U' R U' R2 U2 R U R' U R", makeCOll('T',5,5,2,1,1,6,6,2)),
            18 to Algorithm("T2","R' U R U2 R' L' U R U' L", makeCOll('T',1,2,6,6,2,5,5,1)),
            19 to Algorithm("T3","y l' U' L U l F' L' F", makeCOll('T',1,6,5,2,1,6,2,5)),
            20 to Algorithm("T4","y2 F R U R' U' R U' R' U' R U R' F'", makeCOll('T',6,6,1,2,5,1,2,5)),
            21 to Algorithm("T5","R U R D R' U' R D' R2", makeCOll('T',6,2,1,5,5,6,1,2)),
            22 to Algorithm("T6","R' U R2 D r' U2 r D' R2 U' R", makeCOll('T',1,2,5,5,6,2,1,6)),
            23 to Algorithm("U1","R' U' R U' R' U2 R2 U R' U R U2 R'", makeCOll('U',2,1,5,1,6,6,2,5)),
            24 to Algorithm("U2","R' F R U' R' U' R U R' F' R U R' U' R' F R F' R", makeCOll('U',5,5,2,1,6,6,2,1)),
            25 to Algorithm("U3","y2 R2 D R' U2 R D' R' U2 R", makeCOll('U',6,1,5,2,5,1,6,2)),
            26 to Algorithm("U4","F R U' R' U R U R' U R U' R' F'", makeCOll('U',1,2,6,5,1,2,5,6)),
            27 to Algorithm("U5","R2 D' R U2 R' D R U2 R", makeCOll('U',1,5,2,5,1,6,2,6)),
            28 to Algorithm("U6","R2 D' R U R' D R U R U' R' U' R", makeCOll('U',6,6,1,5,1,2,5,2))
        )

        fun getCOll(index: Int): Algorithm? {
            return coll[index]
        }

        private fun makeCOll(type: Char, vararg indexes: Int): LastLayer {

            val list = mutableListOf(0, 4, 0, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            for (i in collYellows[type]!!) {
                list[i] = 4
            }

            for ((c, i) in collFills[type]!!.withIndex()) {
                list[i] = indexes[c]
            }

            return LastLayer(list)
        }

        // winter variation
        private val wv: HashMap<Int, Algorithm> = hashMapOf(
            1 to Algorithm("Oriented","", makeWV(0,2,8)),
            2 to Algorithm("Rectangle","", makeWV(0,2,14)),
            3 to Algorithm("Rectangle","", makeWV(0,2,15)),
            4 to Algorithm("Snake","", makeWV(0,8,11)),
            5 to Algorithm("Snake","", makeWV(0,8,12)),
            6 to Algorithm("Tank","", makeWV(2,8,20)),
            7 to Algorithm("Tank","", makeWV(2,8,9)),
            8 to Algorithm("Adjacent","", makeWV(0,11,14)),
            9 to Algorithm("Adjacent","", makeWV(0,11,15)),
            10 to Algorithm("Adjacent","", makeWV(0,12,14)),
            11 to Algorithm("Adjacent","", makeWV(0,12,15)),
            12 to Algorithm("Bowtie","", makeWV(2,14,20)),
            13 to Algorithm("Bowtie","", makeWV(2,9,14)),
            14 to Algorithm("Bowtie","", makeWV(2,15,20)),
            15 to Algorithm("Bowtie","", makeWV(2,9,15)),
            16 to Algorithm("Gun (Back)","", makeWV(8,9,11)),
            17 to Algorithm("Gun (Far)","", makeWV(8,11,20)),
            18 to Algorithm("Gun (Near)","", makeWV(8,9,12)),
            19 to Algorithm("Gun (Sides)","", makeWV(8,12,20)),
            20 to Algorithm("H (Front)","", makeWV(12,14,20)),
            21 to Algorithm("H (Side)","", makeWV(9,11,15)),
            22 to Algorithm("Pi (Back)","", makeWV(12,15,20)),
            23 to Algorithm("Pi (Far)","", makeWV(9,12,14)),
            24 to Algorithm("Pi (Front)","", makeWV(9,11,14)),
            25 to Algorithm("Pi (Near)","", makeWV(11,15,20)),
            26 to Algorithm("Sune","", makeWV(11,14,20)),
            27 to Algorithm("Sune","", makeWV(9,12,15))
        )

        fun getWV(index: Int): Algorithm? {
            return coll[index]
        }

        private fun makeWV(vararg indexes: Int): LastLayer {
            val list = mutableListOf(0, 4, 0, 4, 4, 4, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 3, 0, 0)

            for (i in indexes) {
                list[i] = 4
            }

            return LastLayer(list)
        }
    }

}