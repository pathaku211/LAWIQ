package com.example.lawiq

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set the status bar to transparent
        window.statusBarColor = Color.TRANSPARENT

        // Handler to revert status bar color after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GetStarted::class.java))
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
