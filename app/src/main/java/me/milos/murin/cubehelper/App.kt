package me.milos.murin.cubehelper

import android.app.Application
import androidx.annotation.StringRes


class App : Application() {
    companion object {
        lateinit var instance: App private set

        fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
            return instance.getString(stringRes, *formatArgs)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // TODO list
    // Alg of the day selection
    // Alg of the day midnight reset
    // Past solves list fragment
    // Alg of the day saving
    // Add algs for f2l and beyond cfop
    // Timer for different things (3x3, 2x2, 4x4, algs) (save in the same database but with an id for the type 1-3x3, 2-2x2...)
    // themes
}

