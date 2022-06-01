package me.milos.murin.cubehelper

class Timer(var minutes: Int = 0, var seconds: Int = 0, var miliSeconds: Int = 0) {

    override fun toString(): String {
        return String.format("%02d:%02d,%03d", minutes, seconds, miliSeconds)
    }


}