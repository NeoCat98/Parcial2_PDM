package com.aldana.ejemplo14.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aldana.ejemplo14.DAO.GameDao
import com.aldana.ejemplo14.Entities.Game
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Game::class],version = 1,exportSchema = false)
abstract class GameDataBase: RoomDatabase() {
    abstract fun gameDao() : GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDataBase? = null

        fun getInstance(context: Context): GameDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, GameDataBase::class.java, "BasketBallGameDB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}