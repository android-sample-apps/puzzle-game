package com.example.puzzle

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object PuzzleBinding {
    @BindingAdapter("setPuzzle")
    @JvmStatic
    fun setPuzzle(recyclerView : RecyclerView, puzzle : List<Int>){
        if (recyclerView.adapter != null) with(recyclerView.adapter as PuzzleAdapter) { setPuzzle(puzzle) }
    }
}