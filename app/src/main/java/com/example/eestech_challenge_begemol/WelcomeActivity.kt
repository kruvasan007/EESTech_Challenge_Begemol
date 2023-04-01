package com.example.eestech_challenge_begemol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eestech_challenge_begemol.databinding.WelcomeActivityBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginButton.setOnClickListener { goToMain() }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}