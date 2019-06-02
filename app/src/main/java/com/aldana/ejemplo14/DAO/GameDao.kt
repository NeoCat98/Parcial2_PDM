package com.aldana.ejemplo14.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldana.ejemplo14.Entities.Game

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Query("SELECT * FROM game_table")
    fun getAll() : LiveData<List<Game>>

    @Query("DELETE FROM game_table")
    fun deleteAll()

    @Query("DELETE FROM game_table WHERE id = :id")
    fun delete(id:Int)
}