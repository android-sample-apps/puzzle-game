package com.example.puzzle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzle.databinding.ItemPuzzleBinding

class PuzzleAdapter : RecyclerView.Adapter<PuzzleAdapter.PuzzleViewHolder>(){
    private var puzzle = emptyList<Int>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): PuzzleViewHolder
        = PuzzleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_puzzle, parent, false), puzzle.size)

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

    inner class PuzzleViewHolder(itemView : View, size : Int) : RecyclerView.ViewHolder(itemView) {

        private val binding : ItemPuzzleBinding = DataBindingUtil.bind(itemView)!!
        private val puzzleSize = size

        fun bind(puzzle : Int) {
            binding.setVariable(BR.puzzle, puzzle)
            binding.setVariable(BR.size, puzzleSize)
        }
    }
}