package com.aldana.ejemplo14.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.aldana.ejemplo14.R
import com.aldana.ejemplo14.ViewModel.ScoreViewModel
import com.aldana.ejemplo14.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(
                   this, R.layout.activity_main
        )
        binding.team = scoreViewModel



        val teamA = intent.extras.getString(AddActivity.EXTRA_TEAMA)
        val teamB = intent.extras.getString(AddActivity.EXTRA_TEAMB)
        val begin = intent.extras.getString(AddActivity.EXTRA_TIME_BEGIN)
        val date = intent.extras.getString(AddActivity.EXTRA_DATE)

        team_A.text = teamA
        team_B.text = teamB

        bt_team_a_2_p.setOnClickListener{
            scoreViewModel.scoreTeamA = (scoreViewModel.scoreTeamA.toInt()+2).toString()
            binding.team = scoreViewModel
        }
        bt_team_a_3_p.setOnClickListener{
            scoreViewModel.scoreTeamA = (scoreViewModel.scoreTeamA.toInt()+3).toString()
            binding.team = scoreViewModel
        }
        bt_team_b_2_p.setOnClickListener{
            scoreViewModel.scoreTeamB = (scoreViewModel.scoreTeamB.toInt()+2).toString()
            binding.team = scoreViewModel
        }
        bt_team_b_3_p.setOnClickListener {
            scoreViewModel.scoreTeamB = (scoreViewModel.scoreTeamB.toInt() + 3).toString()
            binding.team = scoreViewModel
        }
        bt_team_a_free_throw.setOnClickListener{
            scoreViewModel.scoreTeamA = (scoreViewModel.scoreTeamA.toInt()+1).toString()
            binding.team = scoreViewModel
        }
        bt_team_b_free_throw.setOnClickListener{
            scoreViewModel.scoreTeamB = (scoreViewModel.scoreTeamB.toInt()+1).toString()
            binding.team = scoreViewModel
        }
        bt_reset.setOnClickListener {
            scoreViewModel.scoreTeamA = "0"
            scoreViewModel.scoreTeamB = "0"
            binding.team = scoreViewModel
        }
        bt_save.setOnClickListener {
            val replyIntent = Intent()
            var timeEnd = time_end.currentHour.toString()+":"+time_end.currentMinute.toString()
            replyIntent.putExtra(AddActivity.EXTRA_DATE,date)
            replyIntent.putExtra(AddActivity.EXTRA_TIME_BEGIN, begin)
            replyIntent.putExtra(AddActivity.EXTRA_POINTSTEAMA, scoreViewModel.scoreTeamA)
            replyIntent.putExtra(AddActivity.EXTRA_POINTSTEAMB, scoreViewModel.scoreTeamB)
            replyIntent.putExtra(AddActivity.EXTRA_TEAMA, teamA)
            replyIntent.putExtra(AddActivity.EXTRA_TEAMB, teamB)
            replyIntent.putExtra(AddActivity.EXTRA_TIME_END, timeEnd)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

}
