package com.example.puzzle.clear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.puzzle.R
import com.example.puzzle.databinding.FragmentClearBinding
import com.example.puzzle.util.autoCleared

class ClearFragment : Fragment() {
    private var binding by autoCleared<FragmentClearBinding>()
    private val args by navArgs<ClearFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClearBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.result = args.result
        setBackToStart()
        return binding.root
    }

    private fun setBackToStart() {
        binding.tvClearAgain.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_clearFragment_to_startFragment)
        }
    }
}
