package br.com.francisco.placapp.ui.game.hometeam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import br.com.francisco.placapp.R
import kotlinx.android.synthetic.main.fragment_home_team.*


class HomeTeam : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventBtNext.setOnClickListener {
            /*val ft = activity?.supportFragmentManager?.beginTransaction()
            ft?.replace(R.id.containerGame, AwayTeam())
            ft?.addToBackStack(null)
            ft?.commit()*/
            val intent = Intent("FILTER_HOME_TEAM_NAME")
            intent.putExtra("home_team_name", editText.text.toString())

            LocalBroadcastManager
                .getInstance(requireContext())
                .sendBroadcast(intent)
        }
    }
}