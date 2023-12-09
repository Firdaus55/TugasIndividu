package com.il.e_bike.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.il.e_bike.databinding.ActivityLoginBinding
import com.il.e_bike.utils.SessionManager
import com.il.e_bike.utils.UserRepository
import com.il.e_bike.view.home.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionUser()
        loginButton()
    }

    private fun loginButton() {
        binding.btnLogin.setOnClickListener {
            saveSession()
        }
    }

    private fun sessionUser() {
        val session = SessionManager(this@LoginActivity)
        repository = UserRepository.getInstance(session)

        if (repository.isUserLogin()) {
            login()
        }
    }

    private fun login() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveSession() {
        repository.loginUser(binding.edUsername.text.toString())
        login()
    }
}