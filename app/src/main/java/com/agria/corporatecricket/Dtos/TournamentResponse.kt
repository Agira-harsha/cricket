package com.agria.corporatecricket.Dtos


data class TournamentResponse(
    val tournamentId:Long,
    val tournamentName:String,
    val price:Double,
    val startDate:String,
    val startTime:String
)
