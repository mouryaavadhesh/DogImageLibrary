package com.getimage.avdhesh.assignment

import android.app.Application
import com.getimage.library.avdhesh.DogImages

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DogImages()
    }
}