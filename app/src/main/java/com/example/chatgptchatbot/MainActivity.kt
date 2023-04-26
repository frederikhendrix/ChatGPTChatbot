package com.example.chatgptchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCook = findViewById<Button>(R.id.button_cook)
        buttonCook.setOnClickListener {
            startSecondActivity("Cook")
        }

        val buttonCode = findViewById<Button>(R.id.button_code)
        buttonCode.setOnClickListener {
            startSecondActivity("Programmer")
        }

        val buttonDesign = findViewById<Button>(R.id.button_design)
        buttonDesign.setOnClickListener {
            startSecondActivity("Home Designer")
        }

        val buttonAsk = findViewById<Button>(R.id.button_ask)
        buttonAsk.setOnClickListener {
            startSecondActivity("Electric Technician")
        }

        val buttonQuestion = findViewById<Button>(R.id.button_question)
        buttonQuestion.setOnClickListener {
            startSecondActivity("UX specialist")
        }

        val buttonBuild = findViewById<Button>(R.id.button_build)
        buttonBuild.setOnClickListener {
            startSecondActivity("Interface Tester")
        }
    }

    private fun startSecondActivity(value: String) {
        val intent = Intent(this, SelectImageActivity::class.java)
        intent.putExtra("value", value)
        startActivity(intent)
    }
}