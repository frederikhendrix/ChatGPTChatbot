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
            startSecondActivity("Code")
        }

        val buttonDesign = findViewById<Button>(R.id.button_design)
        buttonDesign.setOnClickListener {
            startSecondActivity("Design")
        }

        val buttonAsk = findViewById<Button>(R.id.button_ask)
        buttonAsk.setOnClickListener {
            startSecondActivity("Ask")
        }

        val buttonQuestion = findViewById<Button>(R.id.button_question)
        buttonQuestion.setOnClickListener {
            startSecondActivity("Question")
        }

        val buttonBuild = findViewById<Button>(R.id.button_build)
        buttonBuild.setOnClickListener {
            startSecondActivity("Build")
        }
    }

    private fun startSecondActivity(value: String) {
        val intent = Intent(this, SelectImageActivity::class.java)
        intent.putExtra("value", value)
        startActivity(intent)
    }
}