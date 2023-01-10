package com.example.android.unscramble.ui.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.android.unscramble.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FinalScoreDialogFragment : DialogFragment() {
    init {
        Log.d("FinalScoreDialogFragment", "FinalScoreDialogFragment created")
    }

    private val sharedViewModel: GameViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, sharedViewModel.score.value))
            .setNegativeButton(getString(R.string.exit)) {_, _ -> exitGame()}
            .setPositiveButton(getString(R.string.play_again)) {_, _ -> restartGame()}
            .create()
    }

    /*
    * Re-initializes the data in the ViewModel and updates the views with the new data, to
    * restart the game.
    */
    private fun restartGame() {
        sharedViewModel.reinitializeData()

        val gameFragmentRef = parentFragment as GameFragment
        gameFragmentRef.setErrorTextField(false)
    }

    /*
     * Exits the game.
     */
    private fun exitGame() {
        activity?.finish()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FinalScoreDialogFragment", "FinalScoreDialogFragment destroyed!")
    }

    companion object {
        const val TAG = "EndOfGameConfirmationDialog"
    }

}