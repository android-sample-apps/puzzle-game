package com.example.puzzle

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PuzzleAdapter : RecyclerView.Adapter<PuzzleAdapter.PuzzleViewHolder>(){
    private var puzzle = emptyList<Int>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): PuzzleViewHolder
        = PuzzleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_puzzle, parent, false))

    override fun getItemCount() = puzzle.size

    override fun onBindViewHolder(holder : PuzzleViewHolder, position : Int) {
        holder.bind(puzzle[position])
    }

    fun setPuzzle(newList : List<Int>) {
        val diffUtilCallBack = PuzzleDiffUtil(puzzle, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.puzzle = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class PuzzleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(number : Int) {
            itemView.findViewById<TextView>(R.id.tv_puzzle).text = number.toString()
            if(number == puzzle.size) itemView.setBackgroundColor(Color.WHITE)
            else itemView.setBackgroundColor(Color.BLACK)
        }
    }
}