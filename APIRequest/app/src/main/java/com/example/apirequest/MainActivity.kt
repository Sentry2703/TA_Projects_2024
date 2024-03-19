package com.example.apirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private var petImageUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.petButton)
        val image = findViewById<ImageView>(R.id.dogImage)
        getNextImage(button, image)
    }

    private fun getDogImageURL() {
        val client = AsyncHttpClient()
        Log.d("Dog", "Did we even enter yet?")

        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                petImageUrl = json.jsonObject.getString("message")
                Log.d("Dog", "response successful $petImageUrl")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog", errorResponse)
            }
        }]
        Log.d("Dog", "Passed thru the img func")
    }

    private fun getNextImage(button: Button, image: ImageView) {
        button.setOnClickListener{
            getDogImageURL()

            Glide.with(this)
                .load(petImageUrl)
                .fitCenter()
                .into(image)
        }
    }
}