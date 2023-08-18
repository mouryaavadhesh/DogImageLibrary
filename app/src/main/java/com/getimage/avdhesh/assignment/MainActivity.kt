package com.getimage.avdhesh.assignment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var dogImageViewModel: DogImageViewModel
    private var imageview: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton = findViewById<Button>(R.id.buttonNext)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)
        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        val enterNumber = findViewById<EditText>(R.id.editTextNumber)
        imageview = findViewById(R.id.imageView)
        setDogLoadingImage()
        dogImageViewModel = ViewModelProvider(this)[DogImageViewModel::class.java]
        dogImageViewModel.dogImage.observe(this) { image ->
            if (image.isNotEmpty()) {
                Glide.with(applicationContext)
                    .load(image)
                    .into(imageview!!)
                nextButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                previousButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                nextButton.isClickable = true
                previousButton.isClickable = true

                if (dogImageViewModel.getImage.dogImages.isEmpty() || dogImageViewModel.getImage.currentIndex == dogImageViewModel.getImage.dogImages.size - 1) {
                    nextButton.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                    nextButton.isClickable = false
                }
                if (dogImageViewModel.getImage.currentIndex == 0) {
                    previousButton.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                    previousButton.isClickable = false

                }
                submitButton.isClickable = true
            }
        }
        dogImageViewModel.fetchDogImage()

        nextButton.setOnClickListener {
            dogImageViewModel.getNextImage()
        }

        previousButton.setOnClickListener {
            dogImageViewModel.getPreviousImage()
        }

        submitButton.setOnClickListener {
            if (enterNumber.text.isNotEmpty()) {
                setDogLoadingImage()
                submitButton.isClickable = false
                // Create a coroutine scope.
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                 coroutineScope.launch {
                    dogImageViewModel.fetchDogImages(this@MainActivity, enterNumber.text.toString().toInt())
                }
            }
        }
    }

    private fun setDogLoadingImage() {
        Glide.with(applicationContext)
            .load(ContextCompat.getDrawable(this, R.mipmap.loading))
            .into(imageview!!)
    }

}
