package com.example.mortgagecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var amountInput: EditText
    private lateinit var rateInput: EditText
    private lateinit var tenureInput: EditText
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountInput = findViewById(R.id.amountInput)
        rateInput = findViewById(R.id.rateInput)
        tenureInput = findViewById(R.id.tenureInput)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            calculateEMI()
        }
    }

    private fun calculateEMI() {
        val principal = amountInput.text.toString().toDouble()
        val annualRate = rateInput.text.toString().toDouble() / 100
        val tenureYears = tenureInput.text.toString().toInt()
        val tenureMonths = tenureYears * 12

        val monthlyRate = annualRate / 12
        val emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths.toDouble())) /
                (Math.pow(1 + monthlyRate, tenureMonths.toDouble()) - 1)

        resultTextView.text = String.format("EMI: %.2f", emi)
    }
}
