package com.example.manovmanga.Auth


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.manovmanga.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordSignUpEditText.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 6){
                binding.passwordSignUpLayout.error = "Password must be at least 6 characters!"
            }else if (text.length > 6){
                binding.passwordSignUpLayout.error = null
            }
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.alreadyRegistered.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener{
            val email = binding.emailSignUpEditText.text.toString().trim()
            val pass = binding.passwordSignUpEditText.text.toString().trim()
            val confirmPass = binding.confirmPasswordSignUpEditText.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener() {
                        if (it.isSuccessful){
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText( this,"Password does not match!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}