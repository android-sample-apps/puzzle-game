package com.example.puzzle.start

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.example.puzzle.R
import com.example.puzzle.databinding.DialogPuzzleBinding
import com.example.puzzle.util.autoCleared

class StartDialog : DialogFragment() {
    private var binding by autoCleared<DialogPuzzleBinding>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPuzzleBinding.inflate(requireActivity().layoutInflater)
        return activity?.let {
            val dialog = AlertDialog.Builder(it).create()
            dialog.setView(binding.root)
            dialog
        } ?: throw IllegalStateException()
    }

    override fun onStart() {
        super.onStart()
        setSizeSelect()
    }

    override fun onResume() {
        super.onResume()
        requireNotNull(dialog).apply {
            requireNotNull(window).setLayout(
                (resources.displayMetrics.widthPixels * 0.9).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(false)
        }
    }

    private fun setSizeSelect() {
        binding.apply {
            tvDialogThree.setOnClickListener {
                movePuzzleFragment()
            }
            tvDialogFour.setOnClickListener {
                movePuzzleFragment()
            }
            tvDialogFive.setOnClickListener {
                movePuzzleFragment()
            }
        }
    }

    private fun movePuzzleFragment() {
        requireActivity().findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_startDialog_to_puzzleFragment)
    }
}
