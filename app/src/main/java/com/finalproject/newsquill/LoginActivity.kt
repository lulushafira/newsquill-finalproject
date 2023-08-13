package com.finalproject.newsquill

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.finalproject.newsquill.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            loginAcc()
        }
    }

    private fun loginAcc() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if(validateForm(email, password)){
            showProgressBar()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        showToast(this, "Logged In successfully")
                        hideProgressBar()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showToast(this, "Log In failed. " + (task.exception?.message))
                        hideProgressBar()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                binding.email.error = "Email is required"
                showToast(this,"Please enter your email")
                binding.email.requestFocus()
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.email.error = "Email is invalid"
                showToast(this, "Please enter valid email")
                binding.email.requestFocus()
                false
            }
            TextUtils.isEmpty(password) -> {
                binding.password.error = "Password is required"
                showToast(this, "Please enter your password")
                binding.password.requestFocus()
                false }
            else -> true
        }
    }
}