package com.example.rnpluginfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rnpluginfg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()

    }

    /**
     * A native method that is implemented by the 'rnpluginfg' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun getPythonHello():String

    companion object {
        // Used to load the 'rnpluginfg' library on application startup.
        init {
            System.loadLibrary("rnpluginfg")
        }
    }
}