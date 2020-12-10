package com.example.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPuzzle = findViewById<RecyclerView>(R.id.rv_puzzle)
        val adapter = PuzzleAdapter()
        rvPuzzle.adapter = adapter
        val list = mutableListOf(1,2,3,4,5,6,7,8,9)
        adapter.setPuzzle(list)
        adapter.shuffle(list)

        val btnLeft = findViewById<Button>(R.id.btn_left)
        val btnUp = findViewById<Button>(R.id.btn_up)
        val btnDown = findViewById<Button>(R.id.btn_down)
        val btnRight = findViewById<Button>(R.id.btn_right)

        btnLeft.setOnClickListener{
            val lastNumber = list.indexOf(9)
            Collections.swap(list, lastNumber, lastNumber+1)
            adapter.setPuzzle(list)
        }

        btnUp.setOnClickListener{
            val lastNumber = list.indexOf(9)
            Collections.swap(list, lastNumber, lastNumber+3)
            adapter.setPuzzle(list)
        }

        btnDown.setOnClickListener{
            val lastNumber = list.indexOf(9)
            Collections.swap(list, lastNumber, lastNumber-3)
            adapter.setPuzzle(list)
        }

        btnRight.setOnClickListener{
            val lastNumber = list.indexOf(9)
            Collections.swap(list, lastNumber, lastNumber-1)
            adapter.setPuzzle(list)
        }
    }
}