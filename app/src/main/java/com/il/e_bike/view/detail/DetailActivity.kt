package com.il.e_bike.view.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.bumptech.glide.Glide
import com.il.e_bike.databinding.ActivityDetailBinding
import com.il.e_bike.model.Bike

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setData()
    }

    private fun setData() {
        val data = intent.getParcelableExtra<Bike>(EXTRA_BIKE)

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(data?.imageUrl)
                .into(ivDetailCar)

            tvDetailName.text = data?.name
            tvDetailPrice.text = data?.harga
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        onBackPressedDispatcher.onBackPressed()
                        true
                    }

                    else -> false
                }
            }
        })
    }

    companion object {
        const val EXTRA_BIKE = "extra_bike"
    }
}