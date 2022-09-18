package com.example.visprogawok

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class Splashsc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashsc)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, home::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}