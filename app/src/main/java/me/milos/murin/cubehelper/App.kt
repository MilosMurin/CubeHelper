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
    // Alg of the day saving after shutdown
    // Alg of the day editing
    // Timer for different things (3x3, 2x2, 4x4, algs) (save in the same database but with an id for the type 1-3x3, 2-2x2...)
    // themes
}

