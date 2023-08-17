package com.getimage.avdhesh.assignment

import android.app.Application
import com.getimage.library.avdhesh.DogImages

class MyApplication : Application() {
    companion object {
        lateinit var dogImages:DogImages
    }
    override fun onCreate() {
        super.onCreate()
        dogImages=  DogImages()
        dogImages.getImage()
    }
}