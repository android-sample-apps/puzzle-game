package com.example.puzzle.adpater

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
            setPuzzleText(puzzle)
            setPuzzleTextColor(puzzle)
            setPuzzleTextSize()
            setPuzzleBackGroundColor(puzzle)
            setPuzzleClickListener()
        }

        private fun setPuzzleText(puzzle: Int) {
            binding.tvPuzzle.text = puzzle.toString()
        }

        private fun setPuzzleTextColor(puzzle: Int) {
            with(binding.tvPuzzle) {
                when {
                    puzzleSize != puzzle -> setTextColor(Color.WHITE)
                    else -> setTextColor(Color.TRANSPARENT)
                }
            }
        }

        private fun setPuzzleTextSize() {
            with(binding.tvPuzzle) {
                when (puzzleSize) {
                    9 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 44F)
                    16 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 32F)
                    25 -> setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                }
            }
        }

        private fun setPuzzleBackGroundColor(puzzle: Int) {
            with(binding.tvPuzzle) {
                when {
                    puzzleSize != puzzle -> setBackgroundColor(Color.BLACK)
                    else -> setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }

        private fun setPuzzleClickListener() {
            binding.tvPuzzle.setOnClickListener {
                puzzleViewModel.move(adapterPosition)
            }
        }
    }

    private class PuzzleDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    }
}
