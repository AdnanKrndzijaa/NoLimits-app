package com.example.nolimits.resetpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityForgotPasswordBinding
import com.example.nolimits.home.Home
import com.example.nolimits.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.RxTextView

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val emailStream = RxTextView.textChanges(binding.email)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailExistAlert(it)
        }

        binding.continueBtn.setOnClickListener {
            val email = binding.email.text.toString().trim()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this) { forgot ->
                    if (forgot.isSuccessful) {
                        Intent(this@ForgotPassword, ForgotPasswordConfirm::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        }
                    } else {
                        Toast.makeText(this, forgot.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.backLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
    private fun showEmailExistAlert(isNotValid: Boolean) {
        if (isNotValid) {
            binding.email.error = "Email is not valid!"
            binding.continueBtn.isEnabled = false
        } else {
            binding.email.error = null
            binding.continueBtn.isEnabled = true
        }
    }
}