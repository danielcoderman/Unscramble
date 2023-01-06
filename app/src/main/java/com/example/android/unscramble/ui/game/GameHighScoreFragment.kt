package com.example.android.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.GameHighScoreFragmentBinding

class GameHighScoreFragment : Fragment() {
    init {
        Log.d("GameHighScoreFragment", "GameHighScoreFragment created")
    }

    private val sharedViewModel: GameViewModel by activityViewModels()

    // Binding object instance with access to the views in the fragment_game_high_score.xml layout
    private lateinit var binding: GameHighScoreFragmentBinding

    // Create a ViewModel the first time the fragment is created.
    // If the fragment is re-created, it receives the same GameViewModel instance created by the
    // first fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.game_high_score_fragment, container, false)
        Log.d("GameHighScoreFragment", "GameHighScoreFragment View created/re-created!")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = sharedViewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameHighScoreFragment", "GameHighScoreFragment destroyed!")
    }
}