package me.milos.murin.cubehelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.common.io.Files
import me.milos.murin.cubehelper.data.Algorithms
import java.io.File
import java.io.FileNotFoundException

/**
 * Hlavna aktivita.
 *
 * Je host pre ostatne fragmenty aplikacie
 */
class MainActivity : AppCompatActivity() {

    private lateinit var file: File

    /**
     * Vytvori hlavnu aktivitu a nacita ulozene data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            file = File(applicationContext.filesDir, "algorithms")
            Algorithms.deserialize(String(Files.toByteArray(file)))
        } catch (_: FileNotFoundException) {
        }

    }

    /**
     * Ulozi data o algoritmoch do dlhodobej pamate zariadenia
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Files.write(Algorithms.serialize().toByteArray(), file)
    }
}