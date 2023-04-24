package com.example.chatgptchatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class ChatActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Retrieve extras from intent
        val stringValue = intent.getStringExtra("STRING_KEY")
        val valList = intent.getSerializableExtra("OBJECT_LIST_KEY") as ArrayList<Object>

        val chatPrompt = "I have a "
        for(thing in valList)
        {
           // chatPrompt += thing.name.toString()
        }
    }
}