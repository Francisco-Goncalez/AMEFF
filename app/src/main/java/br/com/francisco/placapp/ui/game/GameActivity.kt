package br.com.francisco.placapp.ui.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.francisco.placapp.R
import br.com.francisco.placapp.ui.game.awayteam.AwayTeam
import br.com.francisco.placapp.ui.game.event.EventFragment
import br.com.francisco.placapp.ui.game.hometeam.HomeTeam
import br.com.francisco.placapp.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        ivBack.setOnClickListener{
            onBackPressed()
        }

        if (savedInstanceState == null)
            showEventFragment()

        registerBroadcastReceiver()
    }

    private fun showEventFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.containerGame, EventFragment())
        ft.commit()
    }

    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter("FILTER_EVENT_NAME")
        intentFilter.addAction("FILTER_HOME_TEAM_NAME")
        intentFilter.addAction("FILTER_AWAY_TEAM_NAME")

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
    }

    private val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if(intent.hasExtra("event_name")) {
                gameViewModel.eventName = intent.getStringExtra("event_name")
                next(HomeTeam())
            }
            if(intent.hasExtra("home_team_name")) {
                gameViewModel.homeTeamName = intent.getStringExtra("home_team_name")
                next(AwayTeam())
            }
            if(intent.hasExtra("away_team_name")) {
                gameViewModel.awayTeamName = intent.getStringExtra("away_team_name")
                showScore()
            }
        }
    }

    private fun showScore() {

        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("event_name", gameViewModel.eventName)
        intent.putExtra("home_team_name", gameViewModel.homeTeamName)
        intent.putExtra("away_team_name", gameViewModel.awayTeamName)
        startActivity(intent)
        finish()
    }


    private fun next(fragment: Fragment) {
        val ft =supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerGame, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}
