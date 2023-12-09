package com.il.e_bike.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bike(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val harga: String,
) : Parcelable
