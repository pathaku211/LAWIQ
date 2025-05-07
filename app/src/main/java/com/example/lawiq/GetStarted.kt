package com.example.lawiq

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetStarted : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

        if (!isFirstTime) {
            // If not first time, navigate directly to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Close this activity
            return
        }

        setContentView(R.layout.activity_get_started)

        // Find the root view and set the listener for window insets (for immersive experience)
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the TextView to navigate to LoginActivity
        val getStartedButton = findViewById<TextView>(R.id.button)
        getStartedButton.setOnClickListener {
            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            // Update SharedPreferences to indicate the user has seen the Get Started screen
            sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
            finish() // Close this activity
        }
    }
}