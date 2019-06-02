package com.aldana.ejemplo14.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_table")
data class Game(
    val teamA:String,
    val teamB:String,
    val pointsTeamA:Int,
    val pointsTeamB:Int,
    val date:String,
    val gameStart:String,
    val gameEnd:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}