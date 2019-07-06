package br.com.francisco.placapp.ui.game.awayteam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import br.com.francisco.placapp.R
import kotlinx.android.synthetic.main.fragment_away_team.*


class AwayTeam : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_away_team, container, false)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            eventBtNext.setOnClickListener {
                /*val ft = activity?.supportFragmentManager?.beginTransaction()
                ft?.replace(R.id.containerGame, HomeTeam())
                ft?.addToBackStack(null)
                ft?.commit()*/
                val intent = Intent("FILTER_AWAY_TEAM_NAME")
                intent.putExtra("away_team_name", inputAwayTeam.text.toString())

                LocalBroadcastManager
                    .getInstance(requireContext())
                    .sendBroadcast(intent)

            }
        }
}
