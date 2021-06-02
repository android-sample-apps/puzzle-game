package com.example.puzzle.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.puzzle.R
import com.example.puzzle.databinding.FragmentPuzzleBinding
import com.example.puzzle.util.autoCleared

class PuzzleFragment : Fragment() {
    private var binding by autoCleared<FragmentPuzzleBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuzzleBinding.inflate(layoutInflater)
        setBackToStart()
        return binding.root
    }

    private fun setBackToStart() {
        binding.btnPuzzleBack.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_puzzleFragment_to_startFragment)
        }
    }
}
