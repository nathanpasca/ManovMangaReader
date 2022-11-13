package com.example.manovmanga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.manovmanga.Auth.SignInActivity
import com.example.manovmanga.Manga.MangaActivity
import com.example.manovmanga.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.homepageLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
        }

        val MangaActbutton = findViewById<Button>(R.id.homepageMangaButton)
        MangaActbutton.setOnClickListener {
            val intent = Intent(this, MangaActivity::class.java)
            startActivity(intent)
        }


    }
}