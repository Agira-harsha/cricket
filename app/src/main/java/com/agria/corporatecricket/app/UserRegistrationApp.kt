package com.agria.corporatecricket.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.agria.corporatecricket.R
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.screens.DashBoard
import com.agria.corporatecricket.screens.DashBoardDetails
import com.agria.corporatecricket.screens.LoginScreen
import com.agria.corporatecricket.screens.SignUpScreen
import com.agria.corporatecricket.screens.TermsAndConditionsScreen
import com.agria.corporatecricket.screens.TournamentScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserRegistrationApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White)
    {
       Crossfade(targetState = PostRouter.currentScreen) {currentState->
           when(currentState.value){
               is Screen.SignUpScreen->{
                   SignUpScreen()
               }
               is Screen.TermsAndConditionsScreen->{
                   TermsAndConditionsScreen()
               }
               is Screen.LoginScreen->{
                   LoginScreen()
               }
               is Screen.DashBoard->{
                   DashBoard()
               }
               is Screen.DashBoardDetails ->{
                   DashBoardDetails()
               }
               is Screen.TournamentScreen ->{
                   TournamentScreen()
               }


           }
           
       }
    }
}