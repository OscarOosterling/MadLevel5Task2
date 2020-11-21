package com.example.madlevel5task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.Month

import java.util.*

class GameAdapter(private val games:List<Game>):RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun databind(game:Game){
            itemView.tvGameTitle.text = game.title
            itemView.tvGamePlatform.text =game.platform
            itemView.tvReleaseDate.text = createDate(game.releaseYear,game.releaseMonth,game.releaseDay)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    private fun createDate(year:Int,month:Int,day:Int):String{
        val monthName = DateFormatSymbols().months[month-1].toString()
        return "Release: $day $monthName $year"
    }


}