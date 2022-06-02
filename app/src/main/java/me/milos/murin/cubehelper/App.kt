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
}

