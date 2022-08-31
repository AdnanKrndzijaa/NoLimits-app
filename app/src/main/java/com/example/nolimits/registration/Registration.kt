package com.example.nolimits.registration

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nolimits.R
import com.example.nolimits.home.Home
import com.example.nolimits.login.Login
import com.jakewharton.rxbinding2.widget.RxTextView
import com.example.nolimits.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CheckResults")
class Registration : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    var isAllFieldsChecked = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        val emailStream = RxTextView.textChanges(binding.email)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailExistAlert(it)
        }
        val userNameStream = RxTextView.textChanges(binding.username)
            .skipInitialValue()
            .map { username ->
                username.length < 6
            }
        userNameStream.subscribe {
            showTextMinExistAlert(it, "Username")
        }
        val passwordStream = RxTextView.textChanges(binding.password)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showTextMinExistAlert(it, "Password")
        }

        binding.registration.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields()

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    onRegHomeClick()
                }
            }
        })

        binding.haveAcc.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    private fun CheckAllFields(): Boolean {
        if (username!!.length() < 6) {
            username!!.error = "This field is required"
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
        if (text == "Username")
            username.error = if (isNotValid) "$text must have a minimum of 6 characters" else null
        if (text == "Password")
            password.error = if (isNotValid) "$text must have a minimum of 6 characters" else null
    }
    private fun showEmailExistAlert(isNotValid: Boolean) {
        email.error = if (isNotValid) "Email is not valid!" else null
    }

    fun onLoginClick(v: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    fun onRegHomeClick() {
        val username = binding.username.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()
        registerUser(username, email, password)
    }

    private fun registerUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    startActivity(Intent(this, Login::class.java))
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
