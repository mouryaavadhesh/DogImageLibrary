package com.getimage.avdhesh.assignment

import android.content.Context
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getimage.library.avdhesh.DogImages


class DogImageViewModel : ViewModel() {
    val dogImage: MutableLiveData<String> = MutableLiveData()
    lateinit var getImage: DogImages

    fun fetchDogImage() {
        getImage = MyApplication.dogImages
        Handler().postDelayed({
            dogImage.value = getImage.displayImage
        }, 3000)
    }

    suspend  fun fetchDogImages(context: Context, number: Int) {
        getImage.getImage(context, number)
        Handler().postDelayed({
            dogImage.value = getImage.displayImage
        }, 3000)

    }

    fun getNextImage() {
        getImage.getNextImage()
        dogImage.value = getImage.displayImage
    }

    fun getPreviousImage() {
        getImage.getPreviousImage()
        dogImage.value = getImage.displayImage
    }
}