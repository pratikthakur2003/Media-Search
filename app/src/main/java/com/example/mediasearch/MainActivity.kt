package com.example.mediasearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showMedia(view: View) {
        val intent = Intent(this , MediaGallery::class.java)
        val searchQuery = searchText.editableText.toString()
        intent.putExtra("SearchQuery" , searchQuery)

        startActivity(intent)

    }
}