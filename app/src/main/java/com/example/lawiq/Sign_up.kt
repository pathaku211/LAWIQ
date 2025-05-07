package com.example.lawiq

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Sign_up : AppCompatActivity() {

    // Initialize Firebase Auth and Firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val nameField = findViewById<EditText>(R.id.editTextText3)  // Name field
        val emailField = findViewById<EditText>(R.id.editTextText2) // Email field
        val passwordField = findViewById<EditText>(R.id.editTextText1) // Password field
        val confirmPasswordField =
            findViewById<EditText>(R.id.editTextText) // Confirm Password field

        val signUpButton = findViewById<Button>(R.id.button2)
        signUpButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()

            // Validation checks
            when {
                name.isEmpty() -> {
                    nameField.error = "Name is required"
                }

                email.isEmpty() -> {
                    emailField.error = "Email is required"
                }

                password.isEmpty() -> {
                    passwordField.error = "Password is required"
                }

                confirmPassword.isEmpty() -> {
                    confirmPasswordField.error = "Please confirm your password"
                }

                password != confirmPassword -> {
                    confirmPasswordField.error = "Passwords do not match"
                }

                else -> {
                    // If all fields are filled and valid, proceed with registration logic
                    registerUser(name, email, password)
                }
            }
        }

        val loginButton = findViewById<Button>(R.id.button4)
        loginButton.setOnClickListener {
            // Start the LoginActivity when the button is clicked
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        // Create user with Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, save user data to Firestore
                    saveUserToFirestore(name, email, password)
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(
                        baseContext,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUserToFirestore(name: String, email: String, password: String) {
        // Create a new user document in Firestore
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password
        )

        firestore.collection("users") // Collection name
            .add(user)
            .addOnSuccessListener {
                // Show success message first
                Toast.makeText(
                    this,
                    "Registration successful!",
                    Toast.LENGTH_SHORT
                ).show()

                // Delay navigation to allow the Toast to display
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Close the current activity
                }, 2000) // Delay for 2 seconds
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding user: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}