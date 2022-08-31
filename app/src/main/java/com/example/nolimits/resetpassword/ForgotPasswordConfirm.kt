package com.example.nolimits.resetpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityForgotPasswordConfirmBinding
import com.example.nolimits.login.Login

class ForgotPasswordConfirm : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}