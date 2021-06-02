package com.example.puzzle.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return binding.root
    }
}
