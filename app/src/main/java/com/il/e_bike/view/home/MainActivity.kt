package com.il.e_bike.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.il.e_bike.R
import com.il.e_bike.data.DummyData
import com.il.e_bike.databinding.ActivityMainBinding
import com.il.e_bike.model.Bike
import com.il.e_bike.utils.SessionManager
import com.il.e_bike.utils.UserRepository
import com.il.e_bike.view.adapter.MainAdapter
import com.il.e_bike.view.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionUser()

        val data = DummyData.dummyBike
        setupRecycler(data)

        binding.floatingActionButton.setOnClickListener {
            logout()
        }
    }

    private fun setupRecycler(data: List<Bike>) {
        val layout = LinearLayoutManager(this)

        binding.rvBike.apply {
            layoutManager = layout
            setHasFixedSize(true)
            adapter = MainAdapter(data)
        }
    }

    private fun sessionUser() {
        val session = SessionManager(this@MainActivity)
        repository = UserRepository.getInstance(session)
        setupToolbar()
    }

    private fun logout() {
        val session = SessionManager(this)
        repository = UserRepository.getInstance(session)
        repository.logoutUser()
        val i = Intent(this, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar.apply {
            if (repository.getUser() != null) {
                title = "Halo, ${repository.getUser()}"
            } else {
                title = getString(R.string.app_name)
            }
        }
    }
}