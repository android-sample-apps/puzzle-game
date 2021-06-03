package com.example.puzzle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Collections

class PuzzleViewModel : ViewModel() {

    private var size = 0

    private val _puzzle = MutableLiveData<List<Int>>()
    val puzzle: LiveData<List<Int>> = _puzzle

    private val _isPause = MutableLiveData(false)
    val isPause: LiveData<Boolean> = _isPause

    private val _clear = MutableLiveData(false)
    val clear: LiveData<Boolean> = _clear

    private val temp = mutableListOf<Int>()
    private val answerPuzzle = mutableListOf<Int>()

    fun setPuzzle(size: Int) {
        this.size = size
        for (i in 1..size * size) {
            temp.add(i)
            answerPuzzle.add(i)
        }
        temp.shuffle()
        _puzzle.value = temp.toList()
    }

    fun move(direction: Int) {
        val lastNumber = _puzzle.value!!.indexOf(size * size)
        when (direction) {
            1 -> if (lastNumber % size != size - 1) Collections.swap(
                temp,
                lastNumber,
                lastNumber + 1
            )
            2 -> if (lastNumber < (size * (size - 1))) Collections.swap(
                temp,
                lastNumber,
                lastNumber + size
            )
            3 -> if (lastNumber >= size) Collections.swap(
                temp,
                lastNumber,
                lastNumber - size
            )
            4 -> if (lastNumber % size != 0) Collections.swap(
                temp,
                lastNumber,
                lastNumber - 1
            )
        }
        _puzzle.value = temp.toList()
    }

    fun clearCheck() {
        _clear.value = _puzzle.value!! == answerPuzzle
    }

    fun setPause() {
        _isPause.value = !requireNotNull(_isPause.value)
    }
}
