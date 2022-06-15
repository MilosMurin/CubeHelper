package me.milos.murin.cubehelper

import android.app.Application
import android.graphics.Paint
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat


class App : Application() {
    companion object {
        lateinit var instance: App private set

        fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
            return instance.getString(stringRes, *formatArgs)
        }

        fun getPaint(@ColorRes colorRes: Int): Paint {
            val paint = Paint()
            paint.color = ContextCompat.getColor(instance, colorRes)
            return paint
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

