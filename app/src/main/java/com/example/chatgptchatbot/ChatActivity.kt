package com.example.chatgptchatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ChatActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val etQuestion=findViewById<EditText>(R.id.etQuestion)
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
        val txtResponse=findViewById<TextView>(R.id.txtResponse)

        // Retrieve extras from intent
        val stringValue = intent.getStringExtra("STRING_KEY")
        val valList = intent.getSerializableExtra("OBJECT_LIST_KEY") as ArrayList<Object>

        val result = valList.joinToString(separator = " ") { obj ->
            "I have a ${obj.name} with probability ${obj.probability}"
        }

        val personality = "You are a $stringValue and give advice.";

        btnSubmit.setOnClickListener {
            val question=etQuestion.text.toString().trim()
            val promptQuestion = "$personality $result $question"
            Toast.makeText(this,promptQuestion,Toast.LENGTH_SHORT).show()
            if(question.isNotEmpty()){
                getResponse(promptQuestion) { response ->
                    runOnUiThread {
                        txtResponse.text = response
                    }
                }
            }
        }



    }

    fun getResponse(question: String, callback: (String) -> Unit){
        val apiKey="YOUR_API_KEY_OPENAI"
        val url="https://api.openai.com/v1/engines/text-davinci-003/completions"

        val requestBody="""
            {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error","API failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body=response.body?.string()
                if (body != null) {
                    Log.v("data",body)
                }
                else{
                    Log.v("data","empty")
                }
                val jsonObject=JSONObject(body)
                val jsonArray:JSONArray=jsonObject.getJSONArray("choices")
                val textResult=jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }
        })
    }
}