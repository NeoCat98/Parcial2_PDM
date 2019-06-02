package com.aldana.ejemplo14.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aldana.ejemplo14.Entities.Game
import com.aldana.ejemplo14.R
import kotlinx.android.synthetic.main.fragment_basketballgame.view.*

class MyBasketBallGamesRecyclerViewAdapter(
    private val clickListener: (Game) -> Unit,
    private val deleteGame: (Game) -> Unit
) : RecyclerView.Adapter<MyBasketBallGamesRecyclerViewAdapter.ViewHolder>() {

    private var games = emptyList<Game>()


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Game, clickListener: (Game) -> Unit, deleteGame: (Game) -> Unit) = with(itemView){
            team_one.text = item.teamA
            team_two.text = item.teamB
            content_Time.text = "Begin: " + item.gameStart + " " + "End:" + item.gameEnd
            content_Date.text = item.date
            btn_delete.setOnClickListener { deleteGame(item)}
            this.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_basketballgame, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(games[position], clickListener, deleteGame)

    fun setBook(games: List<Game>){
        this.games = games
        notifyDataSetChanged()
    }
}
