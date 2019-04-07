package my.example.fingerboardcheck

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val submitCandidates: Array<String> = arrayOf("NA","ド", "ド♯", "レ", "レ♯", "ミ", "ファ",  "ファ♯","ソ", "ソ♯","ラ","ラ♯", "シ")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitPickerIds = listOf(
            R.id.submitPicker1,
            R.id.submitPicker2,
            R.id.submitPicker3,
            R.id.submitPicker4,
            R.id.submitPicker5,
            R.id.submitPicker6
        )

        submitPickerIds.map { findViewById<NumberPicker>(it) }.forEach {
            it!!.minValue = 0
            it.maxValue = submitCandidates.size - 1
            it.displayedValues = submitCandidates
            it.wrapSelectorWheel = true
        }
    }

    fun setFingerPrint(view: View) {
        val randomFretValues = (0..12).shuffled().slice(0..6)
        val sortedFretValues = randomFretValues.sorted()
        val randomStringPositionValues = (0..5).shuffled().toList()
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

    @SuppressLint("SetTextI18n")
    fun submitAnswer(view: View) {
        val getStringPositionIndex: HashMap<Float, Int> = hashMapOf<Float, Int>(
            25f to 0, 150f to 1, 270f to 2, 420f to 3, 550f to 4, 670f to 5
        )
        val fretIds= listOf(
            R.id.fret1,
            R.id.fret2,
            R.id.fret3,
            R.id.fret4,
            R.id.fret5,
            R.id.fret6
        )
        val submitPickerIds = listOf(
            R.id.submitPicker1,
            R.id.submitPicker2,
            R.id.submitPicker3,
            R.id.submitPicker4,
            R.id.submitPicker5,
            R.id.submitPicker6
        )
        var answers = IntArray(fretIds.size)
        var submits = IntArray(submitPickerIds.size)

        fretIds.map { findViewById<TextView>(it) }.forEachIndexed { idx, it ->
            val question: Array<Int> = arrayOf(it.text.toString().toInt(), getStringPositionIndex[it.y]!!)
            answers[idx] = questionToAnswer(question)
        }

        submitPickerIds.map { findViewById<NumberPicker>(it) }.forEachIndexed { idx, it ->
            submits[idx] = it.value
        }

        fun isCorrectSubmit(submit: Int, answer: Int): Int = if (answer == submit) 1 else 0
        val correctNum: Int = (0 until answers.size).sumBy { isCorrectSubmit(answers[it], submits[it]) }

        val scoreTextView = findViewById<TextView>(R.id.textViewScore)
        scoreTextView.text = "SCORE: $correctNum/${answers.size}"
    }

    fun questionToAnswer(question: Array<Int>): Int {
        val scaleToInt = submitCandidates.withIndex().map {(index,degree) -> degree to index}.toMap()
        var answer = 0

        val (fretPos, stringPos) = question

        answer = when(stringPos) {
            0 -> when(fretPos) {
                0 -> scaleToInt["ミ"]!!
                1 -> scaleToInt["ファ"]!!
                2 -> scaleToInt["ファ♯"]!!
                3 -> scaleToInt["ソ"]!!
                4 -> scaleToInt["ソ♯"]!!
                5 -> scaleToInt["ラ"]!!
                6 -> scaleToInt["ラ♯"]!!
                7 -> scaleToInt["シ"]!!
                8 -> scaleToInt["ド"]!!
                9 -> scaleToInt["ド♯"]!!
                10 -> scaleToInt["レ"]!!
                11 -> scaleToInt["レ♯"]!!
                12 -> scaleToInt["ミ"]!!
                else -> scaleToInt["NA"]!!
            }
            1 -> when(fretPos) {
                0 -> scaleToInt["シ"]!!
                1 -> scaleToInt["ド"]!!
                2 -> scaleToInt["ド♯"]!!
                3 -> scaleToInt["レ"]!!
                4 -> scaleToInt["レ♯"]!!
                5 -> scaleToInt["ミ"]!!
                6 -> scaleToInt["ファ"]!!
                7 -> scaleToInt["ファ♯"]!!
                8 -> scaleToInt["ソ"]!!
                9 -> scaleToInt["ソ♯"]!!
                10 -> scaleToInt["ラ"]!!
                11 -> scaleToInt["ラ♯"]!!
                12 -> scaleToInt["シ"]!!
                else -> scaleToInt["NA"]!!
            }
            2 -> when(fretPos) {
                0 -> scaleToInt["ソ"]!!
                1 -> scaleToInt["ソ♯"]!!
                2 -> scaleToInt["ラ"]!!
                3 -> scaleToInt["ラ♯"]!!
                4 -> scaleToInt["シ"]!!
                5 -> scaleToInt["ド"]!!
                6 -> scaleToInt["ド♯"]!!
                7 -> scaleToInt["レ"]!!
                8 -> scaleToInt["レ♯"]!!
                9 -> scaleToInt["ミ"]!!
                10 -> scaleToInt["ファ"]!!
                11 -> scaleToInt["ファ♯"]!!
                12 -> scaleToInt["ソ"]!!
                else -> scaleToInt["NA"]!!
            }
            3 -> when(fretPos) {
                0 -> scaleToInt["レ"]!!
                1 -> scaleToInt["レ♯"]!!
                2 -> scaleToInt["ミ"]!!
                3 -> scaleToInt["ファ"]!!
                4 -> scaleToInt["ファ♯"]!!
                5 -> scaleToInt["ソ"]!!
                6 -> scaleToInt["ソ♯"]!!
                7 -> scaleToInt["ラ"]!!
                8 -> scaleToInt["ラ♯"]!!
                9 -> scaleToInt["シ"]!!
                10 -> scaleToInt["ド"]!!
                11 -> scaleToInt["ド♯"]!!
                12 -> scaleToInt["レ"]!!
                else -> scaleToInt["NA"]!!
            }
            4 -> when(fretPos) {
                0 -> scaleToInt["ラ"]!!
                1 -> scaleToInt["ラ♯"]!!
                2 -> scaleToInt["シ"]!!
                3 -> scaleToInt["ド"]!!
                4 -> scaleToInt["ド♯"]!!
                5 -> scaleToInt["レ"]!!
                6 -> scaleToInt["レ♯"]!!
                7 -> scaleToInt["ミ"]!!
                8 -> scaleToInt["ファ"]!!
                9 -> scaleToInt["ファ♯"]!!
                10 -> scaleToInt["ソ"]!!
                11 -> scaleToInt["ソ♯"]!!
                12 -> scaleToInt["ラ"]!!
                else -> scaleToInt["NA"]!!
            }
            5 -> when(fretPos) {
                0 -> scaleToInt["ミ"]!!
                1 -> scaleToInt["ファ"]!!
                2 -> scaleToInt["ファ♯"]!!
                3 -> scaleToInt["ソ"]!!
                4 -> scaleToInt["ソ♯"]!!
                5 -> scaleToInt["ラ"]!!
                6 -> scaleToInt["ラ♯"]!!
                7 -> scaleToInt["シ"]!!
                8 -> scaleToInt["ド"]!!
                9 -> scaleToInt["ド♯"]!!
                10 -> scaleToInt["レ"]!!
                11 -> scaleToInt["レ♯"]!!
                12 -> scaleToInt["ミ"]!!
                else -> scaleToInt["NA"]!!
            }
            else -> scaleToInt["NA"]!!
        }
        return answer
    }
}
