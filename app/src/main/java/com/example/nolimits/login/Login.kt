package com.example.nolimits.login

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nolimits.R
import com.example.nolimits.databinding.ActivityLoginBinding
import com.example.nolimits.home.Home
import com.example.nolimits.registration.Registration
import com.example.nolimits.resetpassword.ForgotPassword
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.RxTextView
import java.util.*

@SuppressLint("CheckResults")
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email: EditText
    private lateinit var password: EditText
    var isAllFieldsChecked = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        binding.forgotPasswordLogin.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val emailStream = RxTextView.textChanges(binding.email)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showTextMinExistAlert(it, "Email")
        }
        val passwordStream = RxTextView.textChanges(binding.password)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showTextMinExistAlert(it, "Password")
        }

        binding.login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields()

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    val email = binding.email.text.toString().trim()
                    val password = binding.password.text.toString().trim()
                    loginUser(email, password)
                }
            }
        })
    }
    private fun CheckAllFields(): Boolean {
        if (email!!.length() < 6) {
            email!!.error = "This field is required"
            return false
        }
        if (password!!.length() < 6) {
            password!!.error = "This field is required"
            return false
        }

        // after all validation return true.
        return true
    }

    private fun showTextMinExistAlert(isNotValid: Boolean, text: String) {
        if (text == "Email")
            binding.email.error = if (isNotValid) "$text is not valid!" else null
        if (text == "Password")
            binding.password.error = if (isNotValid) "$text must have a minimum of 6 characters!" else null
    }

    fun onRegisterClick(v: View) {
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { login ->
                if (login.isSuccessful) {
                    startActivity(Intent(this@Login, Home::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    })
                } else {
                    Toast.makeText(this, login.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}