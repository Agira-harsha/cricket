package com.agria.corporatecricket.Router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.agria.corporatecricket.Dtos.LogInResponse


sealed  class Screen {
    object SignUpScreen:Screen()
    object LoginScreen:Screen()
    object DashBoard:Screen()
    object DashBoardDetails:Screen()
    object TournamentScreen:Screen()
    object WinnerScreen:Screen()
    object HelpAndSupportScreen:Screen()
    object  TeamDetailsScreen:Screen()
    object  ScheduledMatchesScreen:Screen()
}
object PostRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.DashBoard)
    fun navigateTo(destination :Screen){
        currentScreen.value=destination
    }

}