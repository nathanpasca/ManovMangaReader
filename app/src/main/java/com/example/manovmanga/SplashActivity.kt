package com.example.manovmanga


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.manovmanga.Auth.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            checkAuth()
        }, 2000)
    }

    private fun checkAuth() {
        // Check if user is signed in (non-null) and update UI accordingly.
        if(FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}