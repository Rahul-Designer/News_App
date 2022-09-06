package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.edt_email
import kotlinx.android.synthetic.main.activity_sign_up.edt_password
import kotlinx.android.synthetic.main.activity_sign_up.log_in

class Sign_up : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        sign_up_btn.setOnClickListener {
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            val number = edt_number.text.toString()
            val password = edt_password.text.toString()

            if (name.isEmpty()) edt_name.error = "Please enter the name"
            if (email.isEmpty()) edt_email.error = "Please enter the email"
            if (number.isEmpty()) edt_number.error = "Please enter the number"
            if (password.isEmpty()) edt_password.error = "Please enter the password"
            if (check.isChecked){
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, NewsActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Please check the checkbox",Toast.LENGTH_SHORT).show()
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
}