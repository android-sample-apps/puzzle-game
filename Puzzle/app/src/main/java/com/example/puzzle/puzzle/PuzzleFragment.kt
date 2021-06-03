package com.example.puzzle.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puzzle.R
import com.example.puzzle.adpater.PuzzleAdapter
import com.example.puzzle.databinding.FragmentPuzzleBinding
import com.example.puzzle.util.autoCleared
import com.example.puzzle.viewmodel.PuzzleViewModel

class PuzzleFragment : Fragment() {
    private var binding by autoCleared<FragmentPuzzleBinding>()
    private val puzzleViewModel by activityViewModels<PuzzleViewModel>()
    private val args by navArgs<PuzzleFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuzzleBinding.inflate(layoutInflater)
        binding.puzzleViewModel = puzzleViewModel
        binding.lifecycleOwner = this@PuzzleFragment
        initRvPuzzle()
        setPuzzle()
        setBackToStart()
        return binding.root
    }

    private fun initRvPuzzle() {
        binding.rvPuzzle.apply {
            adapter = PuzzleAdapter()
            layoutManager = GridLayoutManager(requireContext(), args.size)
        }
    }

    private fun setPuzzle() {
        puzzleViewModel.setPuzzle(args.size)
    }

    private fun setBackToStart() {
        binding.btnPuzzleBack.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_puzzleFragment_to_startFragment)
        }
    }
}
