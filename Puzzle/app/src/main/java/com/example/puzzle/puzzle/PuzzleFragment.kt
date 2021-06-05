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
        setPauseObserver()
        setClearObserver()
        setClearCheckObserver()
        setPuzzle()
        setBackToStart()
        return binding.root
    }

    private fun initRvPuzzle() {
        binding.rvPuzzle.apply {
            adapter = PuzzleAdapter(puzzleViewModel)
            layoutManager = GridLayoutManager(requireContext(), args.size)
        }
    }

    private fun setPauseObserver() {
        puzzleViewModel.isPause.observe(viewLifecycleOwner) { isPause ->
            when (isPause) {
                true -> binding.chronometerPuzzle.stop()
                else -> binding.chronometerPuzzle.start()
            }
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

    private fun setClearObserver() {
        puzzleViewModel.clear.observe(viewLifecycleOwner) { clear ->
            if (clear) {
                requireView().findNavController()
                    .navigate(PuzzleFragmentDirections.actionPuzzleFragmentToClearFragment(binding.chronometerPuzzle.stringResult()))
            }
        }
    }

    private fun setClearCheckObserver() {
        puzzleViewModel.puzzle.observe(viewLifecycleOwner) { puzzle ->
            puzzle?.let {
                puzzleViewModel.clearCheck()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        puzzleViewModel.resetValue()
    }
}
