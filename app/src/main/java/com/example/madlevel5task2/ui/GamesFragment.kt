package com.example.madlevel5task2.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.database.GameRepository
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GamesViewModel
import kotlinx.android.synthetic.main.fragment_games.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private var games= arrayListOf<Game>()
    private var gameAdapter= GameAdapter(games)

    private val viewModel:GamesViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        observeAddGameResult()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_delete_all).isVisible = true
        requireActivity().title = getString(R.string.titlegamepage)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                viewModel.deleteAllGames()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@GamesFragment.games.clear()
            this@GamesFragment.games.addAll(sortGameList(games))
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)

    }
    private fun sortGameList(games:List<Game>):List<Game>{
        var newgames = games.sortedWith(compareBy({it.releaseYear},{it.releaseMonth},{it.releaseDay}))
        Log.e("Games",newgames.toString())
        return newgames
    }

    private fun createItemTouchHelper():ItemTouchHelper{
        val callback=object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target:RecyclerView.ViewHolder
            ):Boolean{
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)
        }
    }
        return ItemTouchHelper(callback)
    }
}