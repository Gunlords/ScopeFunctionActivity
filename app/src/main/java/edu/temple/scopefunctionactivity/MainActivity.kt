package edu.temple.scopefunctionactivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.outputText)
        val button = findViewById<Button>(R.id.refreshButton)

        // Show initial data
        textView.text = getTestDataArray().joinToString("\n")

        updateDisplay(textView)

        //Update data when button is clicked
        button.setOnClickListener {
            updateDisplay(textView)
        }

        // Example test call (Logs output to Logcat)
        Log.d("function output", getTestDataArray().toString())
    }

    // Return a list of random and ascending order
    private fun getTestDataArray() = MutableList(10) { Random.nextInt(1, 100) }.apply { sort() }

    // calculate avarage number, finds the median
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.run {
        val avg = average()
        val sortedList = sorted()
        val median = if (size % 2 == 0)
            (sortedList[size / 2] + sortedList[(size - 1) / 2]) / 2
        else
            sortedList[size / 2]
        avg < median
    }
    private fun updateDisplay(textView: TextView) {
        val numbersList = getTestDataArray().map { it.toDouble() } // Convert to Double for median function
        textView.text = formatOutput(numbersList)
    }

    // Function to format output text
    private fun formatOutput(numbersList: List<Double>): String {
        return "Numbers:\n${numbersList.joinToString("\n")}\n\n" +
                "Avg < Median: ${averageLessThanMedian(numbersList)}"
    }

    //Create a TextView for an item in a collection
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply { text = collection[position].toString() }
}
