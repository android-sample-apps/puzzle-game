package com.example.puzzle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class PuzzleViewModel : ViewModel() {

    private var max = 0

    private val _puzzle = MutableLiveData<List<Int>>()
    val puzzle : LiveData<List<Int>>
        get() = _puzzle

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
        Log.d("tag", lastNumber.toString())
        when (direction) {
            1 -> if(lastNumber%3!=2) Collections.swap(temp, lastNumber, lastNumber+1) // left
            2 -> if(lastNumber<=6) Collections.swap(temp, lastNumber, lastNumber+3) // up
            3 -> if(lastNumber>=3) Collections.swap(temp, lastNumber, lastNumber-3) // down
            4 -> if(lastNumber%3!=0) Collections.swap(temp, lastNumber, lastNumber-1) // right
        }
        _puzzle.value = temp.toList()
    }
}