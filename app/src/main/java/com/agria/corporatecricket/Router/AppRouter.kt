package com.agria.corporatecricket.Router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.agria.corporatecricket.Dtos.LogInResponse


sealed  class Screen {
     object SignUpScreen:Screen()
     object TermsAndConditionsScreen:Screen()
    object LoginScreen:Screen()
    object DashBoard:Screen()
    object DashBoardDetails:Screen()
    object TournamentScreen:Screen()
}
object PostRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)
    fun navigateTo(destination :Screen){
        currentScreen.value=destination
    }

}