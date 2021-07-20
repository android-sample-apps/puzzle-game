package com.example.puzzle.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puzzle.adpater.PuzzleAdapter
import com.example.puzzle.databinding.FragmentPuzzleBinding
import com.example.puzzle.util.autoCleared
import com.example.puzzle.viewmodel.PuzzleViewModel

class PuzzleFragment : Fragment() {
    private var binding by autoCleared<FragmentPuzzleBinding>()
    private val puzzleViewModel by viewModels<PuzzleViewModel>()
    private val args by navArgs<PuzzleFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuzzleBinding.inflate(layoutInflater)
        binding.puzzleViewModel = puzzleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initRvPuzzle()
        setBackClickListener()
        setPuzzle()
        setPuzzleObserver()
        setPauseObserver()
        setClearObserver()
        return binding.root
    }

    private fun initRvPuzzle() {
        binding.rvPuzzle.apply {
            adapter = PuzzleAdapter(puzzleViewModel)
            layoutManager = GridLayoutManager(requireContext(), args.size)
        }
    }

    private fun setBackClickListener() {
        binding.btnPuzzleBack.setOnClickListener {
            requireView().findNavController().popBackStack()
        }
    }

    private fun setPuzzle() {
        puzzleViewModel.setPuzzle(args.size)
    }

    private fun setPuzzleObserver() {
        with(puzzleViewModel) {
            puzzle.observe(viewLifecycleOwner) { puzzle ->
                with(binding.rvPuzzle.adapter as PuzzleAdapter) {
                    submitList(puzzle) {
                        clearCheck()
                    }
                }
            }
        }
    }

    private fun setPauseObserver() {
        puzzleViewModel.isPause.observe(viewLifecycleOwner) { isPause ->
            with(binding.chronometerPuzzle) {
                when (isPause) {
                    true -> stop()
                    false -> start()
                }
            }
        }
    }

    private fun setClearObserver() {
        puzzleViewModel.clear.observe(viewLifecycleOwner) { clear ->
            if (clear) {
                requireView().findNavController()
                    .navigate(PuzzleFragmentDirections.actionPuzzleFragmentToClearFragment(binding.chronometerPuzzle.stringResult()))
            }
        }
    }
}
