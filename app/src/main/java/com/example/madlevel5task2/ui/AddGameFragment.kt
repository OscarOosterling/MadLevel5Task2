package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GamesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_addgame.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel:GamesViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addgame, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabutton.setOnClickListener{
            onAddGame()
        }

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().title = getString(R.string.titleaddgamepage)
        menu.findItem(R.id.action_delete_all).isVisible = false
//        activity?.findViewById<Toolbar>(R.id.home)!!.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }






    private fun onAddGame() {
        val gameTitle = etTitle_addgame.text.toString()
        val gamePlatform = etPlatform_addgame.text.toString()
        val gameDay = etDay_addgame.text.toString()
        val gameMonth = etMonth_addgame.text.toString()
        val gameYear = etYear_addgame.text.toString()

        if(gameTitle.isNotBlank() &&gamePlatform.isNotBlank()&&gameDay.isNotBlank()&&gameMonth.isNotBlank()&&gameYear.isNotBlank()){
            viewModel.insertGame(Game(gameTitle,gamePlatform,gameDay.toInt(),gameMonth.toInt(),gameYear.toInt()))
            findNavController().popBackStack()
        }else {
            Toast.makeText(
                activity,
                "Invalid Title", Toast.LENGTH_SHORT
            ).show()
        }
    }
}