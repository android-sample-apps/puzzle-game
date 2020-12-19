

# Puzzle



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/102695910-be822200-426d-11eb-9c7d-64c274222fcf.gif" width="300" height="600">
    <img src="https://user-images.githubusercontent.com/63637706/102695996-73b4da00-426e-11eb-8e0d-92012ca3b352.gif" width="300" height="600">
</div>
<br>

<br>


## 1. Flow

<img src="https://user-images.githubusercontent.com/63637706/102696314-c8595480-4270-11eb-88f5-9baa6c8d1630.png">

<br>

<br>

## 2. ShowDialog

<img src="https://user-images.githubusercontent.com/63637706/102695465-e8861500-426a-11eb-8770-88e8d17dbb2d.png" width="350" height="600">

```kotlin
private fun showDialog(binding : ActivityMainBinding) {
    val builder = android.app.AlertDialog.Builder(this)
    val layout = LayoutInflater.from(this).inflate(R.layout.dialog_size, null)

    builder.setView(layout)

    val dialog = builder.create()
    
    layout.findViewById<TextView>(R.id.tv_three).setOnClickListener {
        sizeSelect(binding, dialog, 3)
    }
    layout.findViewById<TextView>(R.id.tv_four).setOnClickListener {
        sizeSelect(binding, dialog, 4)
    }
    layout.findViewById<TextView>(R.id.tv_five).setOnClickListener {
        sizeSelect(binding, dialog, 5)
    }

    val back = ColorDrawable(Color.TRANSPARENT)
    val inset = InsetDrawable(back, 100)

    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawable(inset)
    dialog.show()
}
```

```kotlin
fun setPuzzle(size : Int) {
    this.size = size
    for(i in 1..size*size) {
        temp.add(i)
        answerPuzzle.add(i)
    }
    temp.shuffle()
    _puzzle.value = temp.toList()
}
```

<br>

<br>

## 3. PuzzleBinding

```kotlin
object PuzzleBinding {
    @BindingAdapter("setPuzzle")
    @JvmStatic
    fun setPuzzle(recyclerView : RecyclerView, puzzle : List<Int>?) {
        if (recyclerView.adapter != null) 
        with(recyclerView.adapter as PuzzleAdapter) { puzzle?.let { submitList(puzzle) } }
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
```

<br>

<br>

## 4. PuzzleAdapter : ListAdapter

```kotlin
class PuzzleAdapter : ListAdapter<Int, PuzzleAdapter.PuzzleListViewHolder>(PuzzleDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    	= PuzzleListViewHolder(LayoutInflater.from(parent.context)
                               .inflate(R.layout.item_puzzle, parent, false), currentList.size)

    override fun onBindViewHolder(holder: PuzzleListViewHolder, position: Int) 
    	= holder.bind(getItem(position))

    inner class PuzzleListViewHolder(itemView : View, private val size : Int) 
    : RecyclerView.ViewHolder(itemView) {
        private val binding : ItemPuzzleBinding = DataBindingUtil.bind(itemView)!!

        fun bind(puzzle : Int) {
            binding.setVariable(BR.puzzle, puzzle)
            binding.setVariable(BR.size, size)
        }
    }

    private class PuzzleDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int)  = oldItem == newItem
    }
}
```

<br>

<br>

## 5. Move

```kotlin
fun move(direction : Int) {
        val lastNumber = _puzzle.value!!.indexOf(size*size)
        when (direction) {
            1 -> if(lastNumber%size!=size-1) Collections.swap(temp, lastNumber, lastNumber+1)
            	 else _snackBar.value = true // left
            2 -> if(lastNumber<(size*(size-1))) Collections.swap(temp, lastNumber, lastNumber+size)
            	 else _snackBar.value = true // up
            3 -> if(lastNumber>=size) Collections.swap(temp, lastNumber, lastNumber-size)
            	 else _snackBar.value = true // down
            4 -> if(lastNumber%size!=0) Collections.swap(temp, lastNumber, lastNumber-1)
            	 else _snackBar.value = true // right
        }
        _puzzle.value = temp.toList()
}
```

```xml
<ImageButton
	android:id="@+id/btn_left"
    ...
    android:onClick="@{()->puzzleViewModel.move(1)}"
    ... />
<ImageButton
	android:id="@+id/btn_up"
    ...
    android:onClick="@{()->puzzleViewModel.move(2)}"
    ... />
<ImageButton
	android:id="@+id/btn_down"
    ...
    android:onClick="@{()->puzzleViewModel.move(3)}"
    ... />
<ImageButton
	android:id="@+id/btn_right"
    ...
    android:onClick="@{()->puzzleViewModel.move(4)}"
    ... />
```

<br>

<br>

## 6. Wrong Direction

<img src="https://user-images.githubusercontent.com/63637706/102695936-fee1a000-426d-11eb-9878-a036a2460082.gif" width="350" height="600">

```kotlin
viewModel.snackBar.observe(this, Observer { snackBar ->
  snackBar?.let { 
     if(snackBar)
       Snackbar.make(binding.layoutMain, getString(R.string.wrongDirection), Snackbar.LENGTH_SHORT).show() 
  }
})
```
