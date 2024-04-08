package com.agria.corporatecricket.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agria.corporatecricket.Dtos.MatchesResponseDto
import com.agria.corporatecricket.Dtos.RegisterdTeams
import com.agria.corporatecricket.Dtos.TeamRequest
import com.agria.corporatecricket.Dtos.TournamentRegister
import com.agria.corporatecricket.Dtos.TournamentResponse
import com.agria.corporatecricket.Dtos.Winners
import com.agria.corporatecricket.Handlers.service
import com.agria.corporatecricket.Handlers.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class MainViewModel : ViewModel() {

    private val _tournamnetState = mutableStateOf(CricketState())
    val tournaments: State<CricketState> = _tournamnetState
    private val _registeredTeams = MutableStateFlow<List<RegisterdTeams>>(emptyList())
    val registeredTeams: StateFlow<List<RegisterdTeams>> = _registeredTeams
    private val _winnerList: List<Winners>? = null
    var winner: List<Winners>? = _winnerList
    private val _scheduledMatchesState = mutableStateOf<List<MatchesResponseDto>>(emptyList())
    val scheduledMatches: State<List<MatchesResponseDto>> = _scheduledMatchesState


    init {
        fetchTournament()
        fetchWinners()

        token = null


    }

    fun fetchAndProcess(id: Long) {
        viewModelScope.launch {
            var registerdTeams = service.getRegisterdTeams(id.toInt())
            _registeredTeams.value = registerdTeams
            for (registerdTeam in registerdTeams) {
                Log.d("Tag", registerdTeam.registerId.toString())
                Log.d("Tag", registerdTeam.admin)
                Log.d("Tag", registerdTeam.tournamentName)
                Log.d("Tag", registerdTeam.winPrice.toString())

            }
            val response = service.getTournaments()
            _tournamnetState.value = _tournamnetState.value.copy(
                list = response,
                loading = false,
                error = null
            )

        }
    }


    private fun fetchTournament() {
        viewModelScope.launch {
            try {
                val response = service.getTournaments()
                _tournamnetState.value = _tournamnetState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _tournamnetState.value = _tournamnetState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class CricketState(
        val loading: Boolean = true,
        val list: List<TournamentResponse> = emptyList(),
        val error: String? = null
    )

    data class TeamData(
        val list: List<String> = emptyList()
    )

    fun registerTournament(register: TournamentRegister, getToken: String) {
        viewModelScope.launch {
            token = getToken
            var registerDto = service.registerTournament(register)
            Log.d("tag", registerDto.registerId.toString())
            Log.d("tag", registerDto.admin)
            Log.d("tag", registerDto.tournamentName)
            Log.d("tag", registerDto.winPrice.toString())
        }
    }

    fun fetchWinners() {
        viewModelScope.launch {
            try {


                val response = service.getAllWinners()
                response.forEach { winner ->
                    Log.d("Winners", winner.tournamentId.toString())
                    Log.d("Winners", winner.tournamentName)
                    Log.d("Winners", winner.admin)
                    Log.d("Winners", winner.teamName)
                    Log.d("Winners", winner.prize.toString())
                    Log.d("Winners", winner.status)

                }
                winner = response


            } catch (e: Exception) {
                _tournamnetState.value = _tournamnetState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
                Log.d("Error", e.toString())
            }
        }
        data class WinnerState(
            val loading: Boolean = true,
            val list: List<Winners> = emptyList(),
            val error: String? = null
        )
    }

    fun createTeam(request: TeamRequest) {
        viewModelScope.launch {
            try {

                var saveTeam = service.saveTeam(request)
                Log.d("teamId", saveTeam.teamId.toString())
                Log.d("teamName", saveTeam.teamName)
                Log.d("teamId", saveTeam.admin)
                Log.d("Success ", "created")
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

    }

    fun fetchTeam(id: Int) {
        viewModelScope.launch {
            try {
                val saveTeam = service.getTeam(id)
                Log.d("teamId", saveTeam.teamId.toString())
                Log.d("teamName", saveTeam.teamName)
                Log.d("teamId", saveTeam.admin)
                Log.d("Success ", "created")
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }


        }

    }
    fun scheduledMatches(id:Int){
          viewModelScope.launch {
              try {
                  val scheduledMatches = service.scheduledMatches(id)
                  _scheduledMatchesState.value =scheduledMatches
                  scheduledMatches.forEach{
                          matchesResponseDto ->
                      Log.d("tournamentName",matchesResponseDto.tournamentName)
                      Log.d("team1",matchesResponseDto.teamName1)
                      Log.d("team2",matchesResponseDto.teamName2)
                      Log.d("date",matchesResponseDto.date)
                      Log.d("time",matchesResponseDto.time)
                  }

              }
              catch (e:Exception){
                  Log.d("error",e.message.toString())
              }

          }
    }



}