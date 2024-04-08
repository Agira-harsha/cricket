package com.agria.corporatecricket.Handlers

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.agria.corporatecricket.Dtos.MatchesResponseDto
import com.agria.corporatecricket.Dtos.RegisterdTeams
import com.agria.corporatecricket.Dtos.RegistrationResponseDto
import com.agria.corporatecricket.Dtos.TeamRequest
import com.agria.corporatecricket.Dtos.TeamResponse
import com.agria.corporatecricket.Dtos.TournamentRegister
import com.agria.corporatecricket.Dtos.TournamentResponse
import com.agria.corporatecricket.Dtos.Winners
import com.agria.corporatecricket.SessionManager.SessionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
var token:String?=null
val interceptor = Interceptor { chain ->
    val originalRequest = chain.request()

    val requestWithToken: Request = if (token?.isNotEmpty() == true) {
        originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    } else {
        originalRequest
    }

    chain.proceed(requestWithToken)
}
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://0ec6-183-82-34-206.ngrok-free.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

    val service = retrofit.create(ApiService::class.java)


interface ApiService{
    @GET("tournament")
    suspend fun getTournaments():List<TournamentResponse>
    @POST("tournament-registrations")
    suspend fun registerTournament(@Body request: TournamentRegister):RegistrationResponseDto
    @GET("tournament-registrations/{id}")
    suspend fun getRegisterdTeams(@Path("id")id:Int):List<RegisterdTeams>
    @GET("matches")
    suspend fun getAllWinners():List<Winners>
    @POST("team/")
    suspend fun saveTeam(@Body request: TeamRequest): TeamResponse
    @GET("team/login/{id}")
    suspend fun getTeam(@Path("id")id:Int):TeamResponse
    @GET("matches/scheduled/{id}")
    suspend fun  scheduledMatches(@Path("id")id:Int):List<MatchesResponseDto>

}



