package com.aldana.ejemplo14.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aldana.ejemplo14.Entities.Game
import com.aldana.ejemplo14.Adapter.MyBasketBallGamesRecyclerViewAdapter
import com.aldana.ejemplo14.R
import kotlinx.android.synthetic.main.fragment_basketballgame_list.view.*
import java.lang.ClassCastException

class BasketBallGamesFragment : Fragment() {
    private var listenerTool: clickListener? = null
    private lateinit var gameAdapter: MyBasketBallGamesRecyclerViewAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            BasketBallGamesFragment().apply {

            }
    }
    interface clickListener {
        fun itemClick(game: Game)
        fun delete(game: Game)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_basketballgame_list, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is clickListener) {
            listenerTool = context
        } else {
            throw ClassCastException("Se necesita una implementacion de  la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    fun updateAdapter(games: List<Game>) {
        this.gameAdapter.setBook(games)
    }

    private fun initRecyclerView(container: View) {
        gameAdapter =
            MyBasketBallGamesRecyclerViewAdapter({ game: Game ->
                listenerTool?.itemClick(
                    game
                )
            },
                { game: Game -> listenerTool?.delete(game) })
        container.rv_games_list.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context, 1)
            adapter = gameAdapter
        }
    }
}
