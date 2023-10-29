package com.rutvi.mad_assignment_2_21012011123

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)

        val buttonIds = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9
        )

        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onDigitClick(button.text.toString()) }
        }

        findViewById<Button>(R.id.button_dot).setOnClickListener { onDotClick() }
        findViewById<Button>(R.id.button_addition).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.button_subtraction).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.button_multiply).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.button_division).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.button_equals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.button_clear).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.button_bracket).setOnClickListener { onBracketClick("(") }
        findViewById<Button>(R.id.button_bracket_r).setOnClickListener { onBracketClick(")") }
        findViewById<Button>(R.id.button_croxx).setOnClickListener { onCroxxClick() }
    }

    private fun onDigitClick(digit: String) {
        val currentText = inputTextView.text.toString()
        inputTextView.text = currentText + digit
    }

    private fun onDotClick() {
        val currentText = inputTextView.text.toString()
        if (!currentText.contains('.')) {
            inputTextView.text = currentText + "."
        }
    }

    private fun onOperatorClick(operator: String) {
        val currentText = inputTextView.text.toString()
        if (currentText.isNotEmpty() && currentText.last() != ' ') {
            inputTextView.text = currentText + " " + operator + " "
        }
    }

    private fun onClearClick() {
        inputTextView.text = ""
        outputTextView.text = ""
    }

    private fun onEqualsClick() {
        try {
            val expression = ExpressionBuilder(inputTextView.text.toString()).build()
            val result = expression.evaluate()
            outputTextView.text = result.toString()
        } catch (e: Exception) {
            outputTextView.text = "Error"
        }
    }

    private fun onBracketClick(bracket: String) {
        val currentText = inputTextView.text.toString()
        inputTextView.text = currentText + bracket
    }

    private fun onCroxxClick() {
        val currentText = inputTextView.text.toString()
        if (currentText.isNotEmpty()) {
            inputTextView.text = currentText.substring(0, currentText.length - 1)
        }
    }
}
