package com.example.puzzle

import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object PuzzleBinding {
    @BindingAdapter("setPuzzle")
    @JvmStatic
    fun setPuzzle(recyclerView : RecyclerView, puzzle : List<Int>?){
        if (recyclerView.adapter != null) with(recyclerView.adapter as PuzzleAdapter) { puzzle?.let {submitList(puzzle)} }
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

    @BindingAdapter("setTextSize")
    @JvmStatic
    fun setTextSize(textView : TextView, size : Int) {
        textView.apply {
            when (size) {
                9 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 44F)
                16 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 32F)
                25 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            }
        }
    }
}