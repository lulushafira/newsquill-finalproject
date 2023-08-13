package com.finalproject.newsquill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.finalproject.newsquill.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class RegisterActivity : BaseActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            registerAcc()
        }
    }

    private fun registerAcc(){
        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if(validateForm(name, email, password)){
            showProgressBar()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        showToast(this, "Your account is registered successfully")
                        hideProgressBar()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showToast(this, "Registration failed. " + (task.exception?.message))
                        hideProgressBar()
                    }
                }
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                binding.name.error = "Name is required"
                showToast(this, "Please enter your name")
                binding.name.requestFocus()
                false
            }
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
            password.length < 8 -> {
                binding.password.error = "Minimum password is 8 characters"
                showToast(this, "Please enter another password")
                binding.password.requestFocus()
                false }
            else -> true
        }
    }
}