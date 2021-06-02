package com.example.puzzle.start

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.puzzle.databinding.DialogPuzzleBinding
import com.example.puzzle.util.autoCleared
import com.example.puzzle.viewmodel.PuzzleViewModel

class StartDialog : DialogFragment() {
    private var binding by autoCleared<DialogPuzzleBinding>()
    private val puzzleViewModel by activityViewModels<PuzzleViewModel>()

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
                setPuzzle(3)
            }
            tvDialogFour.setOnClickListener {
                setPuzzle(4)
            }
            tvDialogFive.setOnClickListener {
                setPuzzle(5)
            }
        }
    }

    private fun setPuzzle(size: Int) {
        puzzleViewModel.setPuzzle(size)
        requireNotNull(dialog).dismiss()
    }

    companion object {
        const val TAG = "ONE_BUTTON_DIALOG"
    }
}
