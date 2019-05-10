package com.aldana.ejemplo14

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.aldana.ejemplo14.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)


        var binding: ActivityMainBinding = DataBindingUtil.setContentView(
                   this, R.layout.activity_main)
        binding.team = scoreViewModel

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
    }

}
