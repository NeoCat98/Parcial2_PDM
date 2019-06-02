package com.aldana.ejemplo14.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aldana.ejemplo14.Fragment.BasketBallGamesFragment
import com.aldana.ejemplo14.Entities.Game
import com.aldana.ejemplo14.R
import com.aldana.ejemplo14.ViewModel.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*

class ActivityGame : AppCompatActivity(), BasketBallGamesFragment.clickListener {
    private val addActivityRequestCode = 1
    lateinit var viewModel: GameViewModel
    private lateinit var fragment: BasketBallGamesFragment
    private lateinit var games:List<Game>

    override fun itemClick(game: Game) {
        startActivity(
            Intent(this, GameInfoActivity::class.java)
                .putExtra(AddActivity.EXTRA_DATE, game.date)
                .putExtra(AddActivity.EXTRA_POINTSTEAMA, game.pointsTeamA)
                .putExtra(AddActivity.EXTRA_POINTSTEAMB, game.pointsTeamB)
                .putExtra(AddActivity.EXTRA_TEAMA, game.teamA)
                .putExtra(AddActivity.EXTRA_TEAMB, game.teamB)
                .putExtra(AddActivity.EXTRA_TIME_END, game.gameEnd)
                .putExtra(AddActivity.EXTRA_TIME_BEGIN, game.gameStart)
        )
    }

    override fun delete(game: Game) {
        viewModel.delete(game.id)
        Toast.makeText(this,"the game has been removed",Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initFragment()
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.getAllGames().observe(this, Observer {
            if(it!=null){
                this.games = it
                fragment.updateAdapter(it)
            }
        })

        add_game.setOnClickListener {
            val intent = Intent(this@ActivityGame, AddActivity::class.java)
            startActivityForResult(intent, addActivityRequestCode)
        }

        delete_game.setOnClickListener{
            viewModel.deleteAll()
            Toast.makeText(this,"The games has been deleted",Toast.LENGTH_LONG).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val game = Game(
                    data.getStringExtra(AddActivity.EXTRA_TEAMA),
                    data.getStringExtra(AddActivity.EXTRA_TEAMB),
                    data.getStringExtra(AddActivity.EXTRA_POINTSTEAMA).toInt(),
                    data.getStringExtra(AddActivity.EXTRA_POINTSTEAMB).toInt(),
                    data.getStringExtra(AddActivity.EXTRA_DATE),
                    data.getStringExtra(AddActivity.EXTRA_TIME_BEGIN),
                    data.getStringExtra(AddActivity.EXTRA_TIME_END)
                )
                viewModel.insertGame(game)
            }
        } else {
            Toast.makeText(applicationContext,"", Toast.LENGTH_LONG).show()
        }
    }

    private fun initFragment(){
        fragment = BasketBallGamesFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_content, fragment).commit()
    }
}
