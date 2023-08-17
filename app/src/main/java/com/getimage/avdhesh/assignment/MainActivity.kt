package com.getimage.avdhesh.assignment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.getimage.library.avdhesh.DogImages
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    val  getImage=DogImages()
    var image =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image=getImage.getFirstImage()
        setContentView(R.layout.activity_main)

        val nextButton = findViewById<Button>(R.id.buttonNext)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)
        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        val enterNumber = findViewById<EditText>(R.id.editTextNumber)
        val imageview = findViewById<ImageView>(R.id.imageView)


        runBlocking {
            delay(2000) //
            image=getImage.getFirstImage()
            Glide.with(this@MainActivity)
                .load(image)
                .into(imageview)
            println("Hello, world!")
        }


        nextButton.setOnClickListener {
            getImage.getNextImage()
            Glide.with(this@MainActivity)
                .load(getImage.getFirstImage())
                .into(imageview)
        }

        previousButton.setOnClickListener {
            getImage.getPreviousImage()
            Glide.with(this@MainActivity)
                .load(getImage.getFirstImage())
                .into(imageview)
        }

        submitButton.setOnClickListener {
            if(enterNumber.text.isNotEmpty()) {
                getImage.getImage(this, enterNumber.text.toString().toInt())
                Glide.with(this@MainActivity)
                    .load(getImage.getFirstImage())
                    .into(imageview)
            }
        }
    }


}
