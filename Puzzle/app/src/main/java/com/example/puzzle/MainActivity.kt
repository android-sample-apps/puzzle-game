package com.example.puzzle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.puzzle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel : PuzzleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            puzzleViewModel = viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.setPuzzle(9)
        setAdapter(binding)
    }

    private fun setAdapter(binding : ActivityMainBinding) {
        val adapter = PuzzleAdapter()
        binding.rvPuzzle.adapter = adapter
    }
}