package com.example.puzzle.start

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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

    override fun onResume() {
        super.onResume()
        requireNotNull(dialog).apply {
            requireNotNull(window).setLayout((resources.displayMetrics.widthPixels * 0.9).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            setCancelable(false)
        }
    }

    companion object {
        const val TAG = "ONE_BUTTON_DIALOG"
    }
}
