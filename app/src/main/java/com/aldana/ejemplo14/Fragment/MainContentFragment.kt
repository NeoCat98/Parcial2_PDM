package com.aldana.ejemplo14.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aldana.ejemplo14.Entities.Game
import com.aldana.ejemplo14.R
import kotlinx.android.synthetic.main.fragment_main_content.view.*

class MainContentFragment: Fragment() {
    var game : Game? = null

    companion object {
        fun newInstance(games: Game): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.game = games
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content, container, false)
        if( game != null){
            bindData(view,game!!)
        }
        return view
    }

    fun bindData(view: View , game:Game){
            view.tv_team_a_name.text = game.teamA
            view.tv_team_b_name.text = game.teamB
        view.tv_final_score_team_a.text = ""
        view.tv_final_score_team_b.text = ""
            view.tv_date.text = game.date
            view.tv_beginTime.text = game.gameStart
            view.tv_endTime_fragment.text = game.gameEnd
            if(game.teamA.isNotEmpty()) {
                view.text_Winner.text = "WINNER"
                view.tv_final_score_team_a.text = game.pointsTeamA.toString()
                view.tv_final_score_team_b.text = game.pointsTeamB.toString()
                view.date_fragment.text = "Date: "
                view.end_fragment.text = "End: "
                view.begin_fragment.text = "Begin: "
                view.Points.text = "POINTS"
            }
            if(game.pointsTeamA > game.pointsTeamB) view.winner_team_fragment.text = game.teamA
            else view.winner_team_fragment.text = game.teamB
    }
}