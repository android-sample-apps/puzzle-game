package com.example.puzzle.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puzzle.R
import com.example.puzzle.adpater.PuzzleAdapter
import com.example.puzzle.clear.FinishActivity
import com.example.puzzle.databinding.ActivityMainBinding
import com.example.puzzle.viewmodel.PuzzleViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel: PuzzleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            puzzleViewModel = viewModel
            lifecycleOwner = this@MainActivity
        }

        showDialog(binding)
        setAdapter(binding)
        setObserve(binding)
    }

    private fun setAdapter(binding: ActivityMainBinding) {
        val adapter = PuzzleAdapter()
        binding.rvPuzzle.adapter = adapter
    }

    private fun setObserve(binding: ActivityMainBinding) {
        viewModel.snackBar.observe(
            this,
            { snackBar ->
                snackBar?.let {
                    if (snackBar) Snackbar.make(
                        binding.layoutMain,
                        getString(R.string.wrongDirection),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        )

        viewModel.puzzle.observe(
            this,
            { puzzle ->
                puzzle?.let { viewModel.answer() }
            }
        )

        viewModel.clear.observe(
            this,
            { clear ->
                clear?.let {
                    if (clear) {
                        binding.chronometer.stop()
                        val intent = Intent(this, FinishActivity::class.java)
                        intent.putExtra("record", binding.chronometer.text.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
        )
    }

    private fun showDialog(binding: ActivityMainBinding) {
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

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(inset)
        dialog.show()
    }

    private fun sizeSelect(
        binding: ActivityMainBinding,
        dialog: android.app.AlertDialog,
        size: Int
    ) {
        binding.rvPuzzle.layoutManager = GridLayoutManager(this, size)
        viewModel.setPuzzle(size)
        dialog.dismiss()
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
    }
}