package com.agria.corporatecricket.Dtos

data class Winners(
    var tournamentId:Long,
    var tournamentName:String,
    var admin:String,
    var teamName:String,
    var prize:Double,
    var status:String
)
