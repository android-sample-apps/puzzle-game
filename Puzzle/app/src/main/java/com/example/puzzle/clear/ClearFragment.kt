package com.example.puzzle.clear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.puzzle.databinding.FragmentClearBinding
import com.example.puzzle.util.autoCleared

class ClearFragment : Fragment() {
    private var binding by autoCleared<FragmentClearBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClearBinding.inflate(layoutInflater)
        return binding.root
    }
}
