package com.aldana.ejemplo14.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aldana.ejemplo14.R
import kotlinx.android.synthetic.main.activity_game_info.*

class GameInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)
        val teamA = intent.extras.getString(AddActivity.EXTRA_TEAMA)
        val teamB = intent.extras.getString(AddActivity.EXTRA_TEAMB)
        val begin = intent.extras.getString(AddActivity.EXTRA_TIME_BEGIN)
        val date = intent.extras.getString(AddActivity.EXTRA_DATE)
        val end = intent.extras.getString(AddActivity.EXTRA_TIME_END)
        val teamAPoints = intent.extras.getInt(AddActivity.EXTRA_POINTSTEAMA)
        val teamBPoints = intent.extras.getInt(AddActivity.EXTRA_POINTSTEAMB)

        tv_team_a_name.text = teamA
        tv_team_b_name.text = teamB
        tv_final_score_team_a.text = teamAPoints.toString()
        tv_final_score_team_b.text = teamBPoints.toString()
        tv_date.text = date
        tv_beginTime.text = begin
        tv_endTime.text = end

        if(teamAPoints>teamBPoints){
            winner_team.text = teamA
        }
        else{
            winner_team.text = teamB
        }
    }


}
