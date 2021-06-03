package com.example.puzzle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class PuzzleViewModel : ViewModel() {

    private val _puzzle = MutableLiveData<List<Int>>()
    val puzzle: LiveData<List<Int>> = _puzzle

    private val _isPause = MutableLiveData(false)
    val isPause: LiveData<Boolean> = _isPause

    private val _clear = MutableLiveData(false)
    val clear: LiveData<Boolean> = _clear

    fun clearCheck() {
        _clear.value = requireNotNull(_puzzle.value) == answerPuzzle
    }

    fun setPause() {
        _isPause.value = !requireNotNull(_isPause.value)
    }

    private var size = 0
    private val temp = mutableListOf<Int>()
    private val answerPuzzle = mutableListOf<Int>()

    fun setPuzzle(size: Int) {
        this.size = size
        temp.clear()
        for (i in 1..size * size) {
            temp.add(i)
            answerPuzzle.add(i)
        }
        temp.shuffle()
        _puzzle.value = temp.toList()
    }

    fun move(position: Int) {
        when (val lastNumber = _puzzle.value!!.indexOf(size * size)) {
            position + 1, position - 1, position + size, position - size -> {
                swap(lastNumber, position)
            }
        }
    }

    private fun swap(lastNumber: Int, position: Int) {
        Collections.swap(temp, lastNumber, position)
        _puzzle.value = temp.toList()
    }
}
