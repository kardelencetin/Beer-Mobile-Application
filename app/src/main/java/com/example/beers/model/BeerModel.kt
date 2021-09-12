package com.example.beers.model

import com.google.gson.annotations.SerializedName

data class BeerModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("brewers_tips")
    val brewers_tips: String,
    @SerializedName("description")
    val description: String)
