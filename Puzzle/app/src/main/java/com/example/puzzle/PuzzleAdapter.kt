package com.example.puzzle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzle.databinding.ItemPuzzleBinding

class PuzzleAdapter : ListAdapter<Int, PuzzleAdapter.PuzzleListViewHolder>(PuzzleDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = PuzzleListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_puzzle, parent, false), currentList.size)

    override fun onBindViewHolder(holder: PuzzleListViewHolder, position: Int) = holder.bind(getItem(position))

    inner class PuzzleListViewHolder(itemView : View, size : Int) : RecyclerView.ViewHolder(itemView) {

        private val binding : ItemPuzzleBinding = DataBindingUtil.bind(itemView)!!
        private val puzzleSize = size

        fun bind(puzzle : Int) {
            binding.setVariable(BR.puzzle, puzzle)
            binding.setVariable(BR.size, puzzleSize)
        }
    }

    private class PuzzleDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int)  = oldItem == newItem
    }
}