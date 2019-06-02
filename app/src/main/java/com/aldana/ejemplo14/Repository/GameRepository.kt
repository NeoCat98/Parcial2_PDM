package com.aldana.ejemplo14.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.aldana.ejemplo14.DAO.GameDao
import com.aldana.ejemplo14.Entities.Game

class GameRepository(private val gameDao: GameDao) {

    @WorkerThread
    suspend fun insertGame(game : Game){
        gameDao.insert(game)
    }

    fun getAllGames() : LiveData<List<Game>> = gameDao.getAll()

    fun deleteAll() = gameDao.deleteAll()

    fun delete(id:Int) = gameDao.delete(id)
}