package com.example.puzzle

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object PuzzleBinding {
    @BindingAdapter("setPuzzle")
    @JvmStatic
    fun setPuzzle(recyclerView : RecyclerView, puzzle : List<Int>?){
        if (recyclerView.adapter != null) with(recyclerView.adapter as PuzzleAdapter) { puzzle?.let {setPuzzle(puzzle)} }
    }

    @BindingAdapter("setNumber", "setColor")
    @JvmStatic
    fun setNumber(textView : TextView, number : Int?, size : Int?) {
        if(size != number) {
            textView.text = number.toString()
            textView.setBackgroundColor(Color.BLACK)
            textView.setTextColor(Color.WHITE)
        }
        else textView.setBackgroundColor(0)
    }
}