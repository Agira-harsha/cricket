package com.agria.corporatecricket.network

import com.agria.corporatecricket.Dtos.LogInRequest
import com.agria.corporatecricket.Dtos.LogInResponse
import com.agria.corporatecricket.Dtos.SignUpRequest
import com.agria.corporatecricket.Dtos.SignUpResponse
import com.agria.corporatecricket.Dtos.TournamentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("user/sigup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
    @POST("api/user/login")
    fun loginEmail(@Body request: LogInRequest): Call<LogInResponse>

}
