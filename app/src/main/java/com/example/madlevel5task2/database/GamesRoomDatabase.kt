package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.madlevel5task2.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Game::class],version = 1,exportSchema = false)
abstract class GamesRoomDatabase:RoomDatabase(){
    abstract fun gameDao():GameDao

    companion object{
        private const val DATABASE_NAME = "GAMES_DATABASE"

        @Volatile
        private var INSTANCE:GamesRoomDatabase? = null

        fun getDatabase(context: Context):GamesRoomDatabase?{
            if(INSTANCE == null){
                synchronized(GamesRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,GamesRoomDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration().addCallback(object :RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let{database->
                                    CoroutineScope(Dispatchers.IO).launch { database.gameDao().insertGame(Game("Title","",1,1,1))
                                    }
                            }
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}