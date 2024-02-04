package com.example.rnpluginfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rnpluginfg.databinding.ActivityMainBinding
import com.example.rnpluginfg.welcome.LoadingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
        binding.loading.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivityForResult(intent,101)
        }
    }

    /**
     * A native method that is implemented by the 'rnpluginfg' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'rnpluginfg' library on application startup.
        init {
            System.loadLibrary("rnpluginfg")
        }
    }
}