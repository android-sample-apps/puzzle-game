package com.example.puzzle

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.puzzle.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private val viewModel : PuzzleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityFinishBinding = DataBindingUtil.setContentView(this, R.layout.activity_finish)
        binding.apply{
            puzzleViewModel = viewModel
            lifecycleOwner = this@FinishActivity
        }

        setRecord()
        setMoveClickListener(binding)
    }

    private fun setRecord() {
        viewModel.setRecord(intent.getStringExtra("record")!!)
    }

    private fun setMoveClickListener(binding : ActivityFinishBinding) {
        binding.tvAgain.setOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}