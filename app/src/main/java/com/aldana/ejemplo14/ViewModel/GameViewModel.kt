package com.aldana.ejemplo14.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aldana.ejemplo14.Entities.Game
import com.aldana.ejemplo14.Repository.GameRepository
import com.aldana.ejemplo14.RoomDataBase.GameDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository: GameRepository

    init {
        val gameDAO = GameDataBase.getInstance(application).gameDao()
        gameRepository = GameRepository(gameDAO)
    }

    fun insertGame(game : Game) = viewModelScope.launch(Dispatchers.IO){
        gameRepository.insertGame(game)
    }

    fun getAllGames() : LiveData<List<Game>> = gameRepository.getAllGames()

    fun delete(id:Int) = viewModelScope.launch(Dispatchers.IO){
        gameRepository.delete(id)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        gameRepository.deleteAll()
    }
}