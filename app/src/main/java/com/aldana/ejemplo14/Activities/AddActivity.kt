package com.aldana.ejemplo14.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aldana.ejemplo14.R
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class AddActivity : AppCompatActivity() {
    private val addActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        btn_begin.setOnClickListener {
            if(team_a_name.text.isEmpty() || team_b_name.text.isEmpty()){
                Toast.makeText(this,"Llene los campos de equipo",Toast.LENGTH_LONG).show()
            }
            else {
                startActivityForResult(
                    Intent(this, MainActivity::class.java)
                        .putExtra(EXTRA_TEAMA, team_a_name.text.toString())
                        .putExtra(EXTRA_TEAMB, team_b_name.text.toString())
                        .putExtra(
                            EXTRA_DATE,
                            date_picker.dayOfMonth.toString() + "/" + date_picker.month.toString() + "/" + date_picker.year.toString()
                        )
                        .putExtra(EXTRA_TIME_BEGIN, time.currentHour.toString() + ":" + time.currentMinute.toString())
                ,addActivityRequestCode)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_DATE,data.getStringExtra(EXTRA_DATE))
                replyIntent.putExtra(EXTRA_TIME_BEGIN, data.getStringExtra(EXTRA_TIME_BEGIN))
                replyIntent.putExtra(EXTRA_POINTSTEAMA, data.getStringExtra(EXTRA_POINTSTEAMA))
                replyIntent.putExtra(EXTRA_POINTSTEAMB, data.getStringExtra(EXTRA_POINTSTEAMB))
                replyIntent.putExtra(EXTRA_TEAMA, data.getStringExtra(EXTRA_TEAMA))
                replyIntent.putExtra(EXTRA_TEAMB, data.getStringExtra(EXTRA_TEAMB))
                replyIntent.putExtra(EXTRA_TIME_END, data.getStringExtra(EXTRA_TIME_END))
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        } else {
            Toast.makeText(applicationContext,"Hubo un error", Toast.LENGTH_LONG).show()
        }
    }


    companion object {
        const val EXTRA_DATE = "GAME.DATE"
        const val EXTRA_TEAMB = "GAME.TEAM_B"
        const val EXTRA_TIME_BEGIN= "GAME.TIME_END"
        const val EXTRA_TIME_END = "GAME.TIME_BEGIN"
        const val EXTRA_POINTSTEAMB = "GAME.POINTS_TEAM_B"
        const val EXTRA_TEAMA = "GAME.TEAM_A"
        const val EXTRA_POINTSTEAMA = "GAME.POINTS_TEAM_A"
    }
}
