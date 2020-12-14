package com.example.puzzle

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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

        showDialog(binding)
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

        viewModel.puzzle.observe(this, Observer { puzzle ->
            puzzle?.let { if(viewModel.answer()) Snackbar.make(binding.layoutMain, getString(R.string.clear), Snackbar.LENGTH_SHORT).show() }
        })
    }

    private fun showDialog(binding : ActivityMainBinding) {
        val builder = android.app.AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_size, null)

        builder.setView(layout)

        val dialog = builder.create()

        val tvThree = layout.findViewById<TextView>(R.id.tv_three)
        val tvFour = layout.findViewById<TextView>(R.id.tv_four)
        val tvFive = layout.findViewById<TextView>(R.id.tv_five)

        tvThree.setOnClickListener {
            binding.rvPuzzle.layoutManager = GridLayoutManager(this, 3)
            viewModel.setPuzzle(3)
            dialog.dismiss()
        }
        tvFour.setOnClickListener {
            binding.rvPuzzle.layoutManager = GridLayoutManager(this, 4)
            viewModel.setPuzzle(4)
            dialog.dismiss()
        }
        tvFive.setOnClickListener {
            binding.rvPuzzle.layoutManager = GridLayoutManager(this, 5)
            viewModel.setPuzzle(5)
            dialog.dismiss()
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 100)

        dialog.window?.setBackgroundDrawable(inset)
        dialog.show()
    }
}