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
    // 1st list learner cube methods
    // 2nd list learner cube method steps
    // explanation fragment
    // colors
}

