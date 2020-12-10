package com.example.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPuzzle = findViewById<RecyclerView>(R.id.rv_puzzle)
        val adapter = PuzzleAdapter()
        rvPuzzle.adapter = adapter
        rvPuzzle.horizontalDivider()
        rvPuzzle.verticalDivider()
        val list = mutableListOf(1,2,3,4,5,6,7,8,9)
        adapter.setPuzzle(list)
        adapter.shuffle(list)
    }

    private fun RecyclerView.horizontalDivider() {
        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        this.addItemDecoration(divider)
    }

    private fun RecyclerView.verticalDivider() {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        this.addItemDecoration(divider)
    }
}