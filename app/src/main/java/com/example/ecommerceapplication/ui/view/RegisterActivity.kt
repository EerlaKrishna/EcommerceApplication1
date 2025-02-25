package com.example.ecommerceapplication.ui.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerceapplication.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE)

        binding.loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        binding.saveButton.setOnClickListener {
            init()
        }
    }

    private fun init() {
        val username = binding.userName.text.toString()
        val password = binding.password.text.toString()
        val email = binding.email.text.toString()
        val age = binding.age.text.toString().toInt()
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putString("email", email)
        editor.putInt("age", age)
        editor.apply()
        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}