package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.example.madlevel5task2.database.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GamesViewModel(application: Application):AndroidViewModel(application){
    private val gameRepository = GameRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val games:LiveData<List<Game>> = gameRepository.getGames()

    fun insertGame(game: Game){
        ioScope.launch { gameRepository.insertGames(game) }
    }

    fun deleteGame(game: Game){
        ioScope.launch { gameRepository.deleteGames(game) }
    }
    fun deleteAllGames(){
        ioScope.launch { gameRepository.deleteAllGames() }
    }


}