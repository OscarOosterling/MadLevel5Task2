package com.example.madlevel5task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.model.Game

class GameRepository(context: Context){
    private val gameDao:GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGames():LiveData<List<Game>>{
        return gameDao.getGames()
    }

    suspend fun insertGames(game:Game){
        gameDao.insertGame(game)
    }

    suspend fun deleteGames(game:Game){
        gameDao.deleteGame(game)
    }

    suspend fun updateGames(game:Game){
        gameDao.updateGame(game)
    }

    suspend fun deleteAllGames(){
        gameDao.deleteAllGames()
    }
}