package com.getimage.avdhesh.assignment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.getimage.library.avdhesh.DogImages
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var dogImageViewModel: DogImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton = findViewById<Button>(R.id.buttonNext)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)
        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        val enterNumber = findViewById<EditText>(R.id.editTextNumber)
        val imageview = findViewById<ImageView>(R.id.imageView)

        dogImageViewModel = ViewModelProvider(this)[DogImageViewModel::class.java]
        dogImageViewModel.dogImage.observe(this) { image ->
            Glide.with(this@MainActivity)
                .load(image)
                .into(imageview)
            nextButton.setBackgroundColor(resources.getColor(R.color.green))
            previousButton.setBackgroundColor(resources.getColor(R.color.green))
            nextButton.isClickable=true
            previousButton.isClickable=true

            if(dogImageViewModel.getImage.dogImages.isEmpty() || dogImageViewModel.getImage.currentIndex==dogImageViewModel.getImage.dogImages.size-1)
            {
                nextButton.setBackgroundColor(resources.getColor(R.color.red))
                nextButton.isClickable=false
            }
            if(dogImageViewModel.getImage.currentIndex==0)
            {
                previousButton.setBackgroundColor(resources.getColor(R.color.red))
                previousButton.isClickable=false

            }
            submitButton.isClickable=true

        }
        dogImageViewModel.fetchDogImage()

        nextButton.setOnClickListener {
          dogImageViewModel.getNextImage()
        }

        previousButton.setOnClickListener {
           dogImageViewModel.getPreviousImage()
        }

        submitButton.setOnClickListener {
            if(enterNumber.text.isNotEmpty()) {
                submitButton.isClickable=false
                dogImageViewModel.fetchDogImages(this@MainActivity, enterNumber.text.toString().toInt())
            }
        }
    }


}
