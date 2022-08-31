package com.example.nolimits

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nolimits.databinding.ActivityMainBinding
import com.example.nolimits.login.Login
import com.example.nolimits.registration.Registration
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

    }
    fun onLoginClick(v: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
    fun onRegisterClick(v: View) {
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
    }
}
