package com.agria.corporatecricket.Handlers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

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
    private const val URL ="https://0ec6-183-82-34-206.ngrok-free.app/"
    private val okHttp = OkHttpClient.Builder()
    private val builder =
        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())


    private val retrofit = builder.build()


    open fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun createUser(request: SignUpRequest,context: Context) {
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
                    Log.d("userId", it.userId.toString())
                    Log.d("email", it.email)
                    Log.d("userName", it.userName)
                    Toast.makeText(context, "registration successfully.", Toast.LENGTH_SHORT).show()
                    PostRouter.navigateTo(Screen.LoginScreen)

                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
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
                        Log.d("userId", body.userId.toString())
                        Log.d("userName", body.userName)
                        Log.d("email", body.email)
                        Log.d("token", body.token)

                        SessionManager.storeLoginResponse(context, it)
                        Toast.makeText(context, "Login successfully.", Toast.LENGTH_SHORT).show()
                        PostRouter.navigateTo(Screen.DashBoardDetails)

                    }

                }
            }

            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}








