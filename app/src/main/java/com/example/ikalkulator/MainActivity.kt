package com.example.ikalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentNumber = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.resultView)

        listOf(R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9)
            .forEach { findViewById<Button>(it).setOnClickListener { v ->
                currentNumber += (v as Button).text
                display.text = currentNumber
            }}

        findViewById<Button>(R.id.btnAdd).setOnClickListener { op("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { op("−") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { op("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { op("÷") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (currentNumber.isNotEmpty() && operator.isNotEmpty()) {
                val second = currentNumber.toDouble()
                val result = when (operator) {
                    "+" -> firstNumber + second
                    "−" -> firstNumber - second
                    "×" -> firstNumber * second
                    "÷" -> firstNumber / second
                    else -> 0.0
                }
                display.text = result.toString()
                currentNumber = result.toString()
                operator = ""
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentNumber = ""
            operator = ""
            firstNumber = 0.0
            display.text = "0"
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            currentNumber = currentNumber.dropLast(1)
            display.text = if (currentNumber.isEmpty()) "0" else currentNumber
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener {
            if (!currentNumber.contains(".")) {
                currentNumber += "."
                display.text = currentNumber
            }
        }

        findViewById<Button>(R.id.btnPercent).setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = (currentNumber.toDouble() / 100).toString()
                display.text = currentNumber
            }
        }
    }

    private fun op(o: String) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber.toDouble()
            operator = o
            currentNumber = ""
        }
    }
}