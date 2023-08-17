package com.getimage.library.avdhesh

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DogImages() {

    private var dogImages: List<String> = emptyList()
    private var currentIndex = 0
    private var currentImage = ""
    private var apiInterface: APIInterface? = null

    init {
        apiInterface = APIClient.client!!.create(APIInterface::class.java)
        getImage()
    }

    fun getImage() {
        val call1: Call<Image?> = apiInterface!!.doGetImage()
        call1.enqueue(object : Callback<Image?> {
            override fun onResponse(call: Call<Image?>, response: Response<Image?>) {
                currentImage = response.body()!!.message!!
            }

            override fun onFailure(call: Call<Image?>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getImage(context: Context, number: Int) {
        if (number in 1..10) {
            val call1: Call<ImageList> = apiInterface!!.doGetImages(number)
            call1.enqueue(object : Callback<ImageList> {
                override fun onResponse(call: Call<ImageList>, response: Response<ImageList>) {
                    dogImages = response.body()!!.message
                    currentImage = dogImages[0]
                }

                override fun onFailure(call: Call<ImageList>, t: Throwable) {
                    call.cancel()
                }
            })
        } else {
            val message = "Enter number should be between less then 10 and more than 0."
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }


    fun getFirstImage(): String {
        return currentImage
    }

    fun getNextImage() {
        if (dogImages.isNotEmpty() && currentIndex < dogImages.size - 1) {
            currentIndex++
            currentImage = dogImages[currentIndex]
        }
    }

    fun getPreviousImage() {
        if (dogImages.isNotEmpty() && currentIndex >= 1) {
            currentIndex--
            currentImage = dogImages[currentIndex]
        }
    }
}