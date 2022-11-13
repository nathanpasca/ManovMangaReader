package com.example.manovmanga.Auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.manovmanga.databinding.ActivitySignInBinding
import com.example.manovmanga.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerHere.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignIn.setOnClickListener {
            val email = binding.emailSignInEditText.text.toString().trim()
            val pass = binding.passwordSignInEditText.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_SHORT).show()
            }

        }
        binding.passwordSignInEditText.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 6){
                binding.passwordSignInLayout.error = "Password Length must be higher than 6!"
            }else if (text.length > 6){
                binding.passwordSignInLayout.error = null
            }
        }

    }
}