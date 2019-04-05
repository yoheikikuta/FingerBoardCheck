package my.example.fingerboardcheck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setFingerPrint(view: View) {
        val randomFretValues = List(6) { Random.nextInt(0,12+1) }
        val sortedFretValues = randomFretValues.sorted()

        val showFretTextView1 = findViewById<TextView>(R.id.fret1)
        val showFretTextView2 = findViewById<TextView>(R.id.fret2)
        val showFretTextView3 = findViewById<TextView>(R.id.fret3)
        val showFretTextView4 = findViewById<TextView>(R.id.fret4)
        val showFretTextView5 = findViewById<TextView>(R.id.fret5)
        val showFretTextView6 = findViewById<TextView>(R.id.fret6)
        showFretTextView1.text = sortedFretValues[0].toString()
        showFretTextView2.text = sortedFretValues[1].toString()
        showFretTextView3.text = sortedFretValues[2].toString()
        showFretTextView4.text = sortedFretValues[3].toString()
        showFretTextView5.text = sortedFretValues[4].toString()
        showFretTextView6.text = sortedFretValues[5].toString()
    }
}
