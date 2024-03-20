package com.agria.corporatecricket.Handlers

import android.content.Context
import android.util.Log
import com.agria.corporatecricket.Dtos.LogInRequest
import com.agria.corporatecricket.Dtos.LogInResponse
import com.agria.corporatecricket.Dtos.SignUpRequest
import com.agria.corporatecricket.Dtos.SignUpResponse
import com.agria.corporatecricket.Dtos.TournamentResponse
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.SessionManager.SessionManager
import com.agria.corporatecricket.network.ApiService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://092a-183-82-34-206.ngrok-free.app/"
    private val okHttp = OkHttpClient.Builder()
    private val builder =
        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())


    private val retrofit = builder.build()


    open fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun createUser(request: SignUpRequest) {
        val createUser = buildService(ApiService::class.java)
        val signUp = createUser.signUp(request)
        signUp.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                val upResponse = response.body()
                Log.d("Tag", response.toString())
                upResponse?.let {
                    upResponse.userId = upResponse.userId;
                    upResponse.userName = upResponse.userName
                    upResponse.email = upResponse.email
                    Log.d("tag", it.userId.toString())
                    Log.d("tag", it.email.toString())
                    Log.d("tag", it.userName.toString())
                    PostRouter.navigateTo(Screen.LoginScreen)
                }


            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    fun loadDetail(id: Int) {
        val details = buildService(ApiService::class.java)
        val login = details.login(id)
        login.enqueue(object : Callback<LogInResponse> {
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                val body = response.body()
                Log.d("Tag", response.toString())
                body?.let {
                    body.userId = body.userId;
                    body.userName = body.userName
                    body.email = body.email
                    Log.d("Tag", body.userId.toString())
                    Log.d("Tag", body.userName.toString())
                    Log.d("Tag", body.email.toString())


                }


            }

            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }


    fun logUser(request: LogInRequest, context: Context) {

        val logUser = buildService(ApiService::class.java)
        val loginEmail = logUser.loginEmail(request)
        loginEmail.enqueue(object : Callback<LogInResponse> {


            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {

                if (response.isSuccessful) {
                    val body = response.body()

                    Log.d("Tag", response.toString())
                    body?.let {
                        body.userId = body.userId;
                        body.userName = body.userName
                        body.email = body.email
                        Log.d("Tag", body.userId.toString())
                        Log.d("Tag", body.userName.toString())
                        Log.d("Tag", body.email.toString())
                        PostRouter.navigateTo(Screen.DashBoard)
                        SessionManager.storeLoginResponse(context, it)

                    }

                }
            }


            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

    fun TouramentDteails() {
        val buildService = buildService(ApiService::class.java)

        val tournamentInfo = buildService.tournamentInfo()
//        tournamentInfo.enqueue(object : Callback<List<TournamentResponse>> {
//            override fun onResponse(
//                call: Call<List<TournamentResponse>>,
//                response: Response<List<TournamentResponse>>
//            ) {
//                val body = response.body()
//                body?.let {
                    for (tournament in tournamentInfo) {
                        Log.d("id", tournament.tournamentId.toString())
                        Log.d("name", tournament.tournamentName.toString())
                        Log.d("prize", tournament.price.toString())
                        Log.d("date", tournament.startDate.toString())
                        Log.d("time", tournament.startTime.toString())
                    }

//                }
//
//            }

//            override fun onFailure(call: Call<List<TournamentResponse>>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//
//

    }

}








