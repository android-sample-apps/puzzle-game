package com.example.puzzle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.puzzle.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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
        setObserve(binding)
    }

    private fun setAdapter(binding : ActivityMainBinding) {
        val adapter = PuzzleAdapter()
        binding.rvPuzzle.adapter = adapter
    }

    private fun setObserve(binding : ActivityMainBinding) {
        viewModel.snackBar.observe(this, Observer { snackBar ->
            snackBar?.let { if(snackBar) Snackbar.make(binding.layoutMain, getString(R.string.wrongDirection), Snackbar.LENGTH_SHORT).show() }
        })
    }
}