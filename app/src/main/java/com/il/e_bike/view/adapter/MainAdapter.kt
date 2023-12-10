package com.il.e_bike.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.il.e_bike.databinding.ItemBikeBinding
import com.il.e_bike.model.Bike
import com.il.e_bike.view.detail.DetailActivity

class MainAdapter(
    private val data: List<Bike>,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemBikeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBikeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bike = data[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(bike.imageUrl)
                .into(ivCar)
            tvName.text = bike.name
            tvPrice.text = bike.harga
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_BIKE, bike)
            holder.itemView.context.startActivity(intent)
        }
    }
}