package com.example.beers.service

import com.example.beers.model.BeerModel
import retrofit2.Call
import retrofit2.http.GET

interface BeerApi {
    @GET("/v2/beers/")
    fun getData(): Call<List<BeerModel>>
}