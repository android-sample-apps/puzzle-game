package com.example.puzzle.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzle.BR
import com.example.puzzle.databinding.ItemPuzzleBinding
import com.example.puzzle.viewmodel.PuzzleViewModel

class PuzzleAdapter(private val puzzleViewModel: PuzzleViewModel) :
    ListAdapter<Int, PuzzleAdapter.PuzzleListViewHolder>(PuzzleDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PuzzleListViewHolder(
            ItemPuzzleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            currentList.size,
            puzzleViewModel
        )

    override fun onBindViewHolder(holder: PuzzleListViewHolder, position: Int) =
        holder.bind(getItem(position))

    class PuzzleListViewHolder(
        private val binding: ItemPuzzleBinding,
        private val puzzleSize: Int,
        private val puzzleViewModel: PuzzleViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(puzzle: Int) {
            with(binding) {
                setVariable(BR.puzzle, puzzle)
                setVariable(BR.size, puzzleSize)
                tvPuzzle.setOnClickListener {
                    puzzleViewModel.move(adapterPosition)
                }
            }
        }
    }

    private class PuzzleDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    }
}
