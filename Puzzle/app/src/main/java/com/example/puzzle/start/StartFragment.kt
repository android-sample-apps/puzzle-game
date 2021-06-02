package com.example.puzzle.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.puzzle.R
import com.example.puzzle.databinding.FragmentStartBinding
import com.example.puzzle.util.autoCleared

class StartFragment : Fragment() {
    private var binding by autoCleared<FragmentStartBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        setStartGame()
        return binding.root
    }

    private fun setStartGame() {
        binding.tvStartNewGame.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_startFragment_to_puzzleFragment)
        }
    }
}
