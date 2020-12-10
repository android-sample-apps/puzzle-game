package com.example.puzzle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class PuzzleViewModel : ViewModel() {

    private var max = 0

    private val _puzzle = MutableLiveData<List<Int>>()
    val puzzle : LiveData<List<Int>>
        get() = _puzzle

    private val _snackBar = MutableLiveData<Boolean>()
    val snackBar : LiveData<Boolean>
        get() = _snackBar

    private val temp = mutableListOf<Int>()

    fun setPuzzle(max : Int) {
        this.max = max
        for(i in 1..max)
            temp.add(i)
        temp.shuffle()
        _puzzle.value = temp.toList()
    }

    fun move(direction : Int) {
        val lastNumber = _puzzle.value!!.indexOf(max)
        when (direction) {
            1 -> if(lastNumber%3!=2) Collections.swap(temp, lastNumber, lastNumber+1) else _snackBar.value = true // left
            2 -> if(lastNumber<6) Collections.swap(temp, lastNumber, lastNumber+3) else _snackBar.value = true // up
            3 -> if(lastNumber>=3) Collections.swap(temp, lastNumber, lastNumber-3) else _snackBar.value = true // down
            4 -> if(lastNumber%3!=0) Collections.swap(temp, lastNumber, lastNumber-1) else _snackBar.value = true // right
        }
        _puzzle.value = temp.toList()
    }
}