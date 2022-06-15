package me.milos.murin.cubehelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.common.io.Files
import me.milos.murin.cubehelper.data.Algorithms
import java.io.File
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {

    private lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            file = File(applicationContext.filesDir, "algorithms")
            Algorithms.deserialize(String(Files.toByteArray(file)))
        } catch (_: FileNotFoundException) {
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Files.write(Algorithms.serialize().toByteArray(), file)
    }
}