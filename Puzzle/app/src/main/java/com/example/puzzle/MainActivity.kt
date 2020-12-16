package com.example.puzzle

import android.content.Intent
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
            puzzle?.let { viewModel.answer() }
        })

        viewModel.clear.observe(this, Observer {clear ->
            clear?.let { if(clear) startActivity(Intent(this, FinishActivity::class.java)) }
        })

    }

    private fun showDialog(binding : ActivityMainBinding) {
        val builder = android.app.AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_size, null)

        builder.setView(layout)

        val dialog = builder.create()

        layout.findViewById<TextView>(R.id.tv_three).setOnClickListener {
            sizeSelect(binding, dialog, 3)
        }
        layout.findViewById<TextView>(R.id.tv_four).setOnClickListener {
            sizeSelect(binding, dialog, 4)
        }
        layout.findViewById<TextView>(R.id.tv_five).setOnClickListener {
            sizeSelect(binding, dialog, 5)
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 100)

        dialog.window?.setBackgroundDrawable(inset)
        dialog.show()
    }

    private fun sizeSelect(binding : ActivityMainBinding, dialog : android.app.AlertDialog, size : Int) {
        binding.rvPuzzle.layoutManager = GridLayoutManager(this, size)
        viewModel.setPuzzle(size)
        dialog.dismiss()
    }
}