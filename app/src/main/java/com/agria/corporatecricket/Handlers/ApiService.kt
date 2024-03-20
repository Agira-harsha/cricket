package com.agria.corporatecricket.Handlers

import com.agria.corporatecricket.Dtos.TournamentResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://092a-183-82-34-206.ngrok-free.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val recipeService = retrofit.create(ApiService::class.java)

interface ApiService{
    @GET("tournament")
    suspend fun getCategories():List<TournamentResponse>

}