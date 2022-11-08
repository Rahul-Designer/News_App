package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class Sign_up : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        // Sign Up
        sign_up_btn.setOnClickListener {
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            val number = edt_number.text.toString()
            val password = edt_password.text.toString()

            if (name.isEmpty()) edt_name.error = "Please enter the name"
            if (email.isEmpty()) edt_email.error = "Please enter the email"
            if (number.isEmpty()) edt_number.error = "Please enter the number"
            if (isValidPassword(password)) {
                if (check.isChecked) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                // Shared Preference
                                val pref = getSharedPreferences("login", MODE_PRIVATE)
                                val editor = pref.edit()
                                editor.putBoolean("flag", true)
                                editor.apply()
                                startActivity(Intent(this, NewsActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Please check the checkbox", Toast.LENGTH_SHORT).show()
                }
            } else {
                edt_password.error = "Please enter valid password "
            }
        }

        sign_in.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        log_in.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }
                .firstOrNull() == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false
        return true
    }
}