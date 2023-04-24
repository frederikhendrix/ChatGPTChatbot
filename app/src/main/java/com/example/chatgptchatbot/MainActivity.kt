package com.example.chatgptchatbot

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    // Define the button and imageview type variable
    private lateinit var cameraOpenId: Button
    lateinit var clickImageId: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.
        cameraOpenId = findViewById(R.id.camera_button)
        clickImageId = findViewById(R.id.click_image)

        // Camera_open button is for open the camera and add the setOnClickListener in this button
        cameraOpenId.setOnClickListener(View.OnClickListener { v: View? ->
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(cameraIntent, pic_id)
        })
    }

    // This method will help to retrieve the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?

            // Convert the Bitmap to a byte array
            val stream = ByteArrayOutputStream()
            photo?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            // Load the object detection model using KotlinDL
            val model = ObjectDetectionModel()

            // Feed the image into the object detection model and obtain the bounding boxes and labels for the detected objects
            val results = model.detectObjects(byteArray)
            val boundingBoxes = results.boundingBoxes
            val labels = results.labels

            // Display the image with the bounding boxes and labels overlaid on top
            val overlay = drawBoundingBoxes(photo!!, boundingBoxes, labels)
            clickImageId.setImageBitmap(overlay)

        }
    }

    companion object {
        // Define the pic id
        private const val pic_id = 123
    }
}