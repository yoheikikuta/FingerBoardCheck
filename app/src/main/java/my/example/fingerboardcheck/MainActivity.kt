package my.example.fingerboardcheck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        if (numberPicker != null) {
            val values = arrayOf("ド", "レ", "ミ", "ファ", "ソ", "ラ", "シ")
            numberPicker.minValue = 0
            numberPicker.maxValue = values.size - 1
            numberPicker.displayedValues = values
            numberPicker.wrapSelectorWheel = true
            numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
                val text = "Changed from " + values[oldVal] + " to " + values[newVal]
                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setFingerPrint(view: View) {
        val randomFretValues = (0..13).shuffled().slice(0..6)
        val sortedFretValues = randomFretValues.sorted()
        val randomStringPositionValues = List(6) { Random.nextInt(0,6) }
        val getStringPositionCoordinates: HashMap<Int, Float> = hashMapOf<Int,Float>(
            0 to 25f, 1 to 150f, 2 to 270f, 3 to 420f, 4 to 550f, 5 to 670f
        )

        val showFretTextView1 = findViewById<TextView>(R.id.fret1)
        val showFretTextView2 = findViewById<TextView>(R.id.fret2)
        val showFretTextView3 = findViewById<TextView>(R.id.fret3)
        val showFretTextView4 = findViewById<TextView>(R.id.fret4)
        val showFretTextView5 = findViewById<TextView>(R.id.fret5)
        val showFretTextView6 = findViewById<TextView>(R.id.fret6)
        showFretTextView1.text = sortedFretValues[0].toString()
        showFretTextView1.y = getStringPositionCoordinates[randomStringPositionValues[0]]!!
        showFretTextView2.text = sortedFretValues[1].toString()
        showFretTextView2.y = getStringPositionCoordinates[randomStringPositionValues[1]]!!
        showFretTextView3.text = sortedFretValues[2].toString()
        showFretTextView3.y = getStringPositionCoordinates[randomStringPositionValues[2]]!!
        showFretTextView4.text = sortedFretValues[3].toString()
        showFretTextView4.y = getStringPositionCoordinates[randomStringPositionValues[3]]!!
        showFretTextView5.text = sortedFretValues[4].toString()
        showFretTextView5.y = getStringPositionCoordinates[randomStringPositionValues[4]]!!
        showFretTextView6.text = sortedFretValues[5].toString()
        showFretTextView6.y = getStringPositionCoordinates[randomStringPositionValues[5]]!!
    }

    fun submitAnswer(view: View) {
        val getStringPositionIndex: HashMap<Float, Int> = hashMapOf<Float, Int>(
            25f to 0, 150f to 1, 270f to 2, 420f to 3, 550f to 4, 670f to 5
        )
        val showFretTextView1 = findViewById<TextView>(R.id.fret1)
        val question1: Array<Int> = arrayOf(
            showFretTextView1.text.toString().toInt(),
            getStringPositionIndex[showFretTextView1.y]!!
        )

        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        val answer1 = numberPicker.value

        val correctNum: Int = if (question1[1] == answer1) 1 else 0

        val scoreTextView = findViewById<TextView>(R.id.textViewScore)
        scoreTextView.text = "SCORE: ${correctNum.toString()}/6"
    }
}
