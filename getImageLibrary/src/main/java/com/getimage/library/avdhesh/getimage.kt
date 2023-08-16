package com.getimage.library.avdhesh

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*


class DogImages {

    private val client = OkHttpClient()
    private val gson = Gson()
    private var dogImages = mutableListOf<String>()
    private var currentIndex = 0
    private var firstImage = ""


    fun getImage(): String {
        val url = "https://dog.ceo/api/breeds/image/random"
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val json = response.body?.string() ?: ""
            val type = object : TypeToken<Map<String, String>>() {}.type
            val map: Map<String, String> = gson.fromJson(json, type)
            firstImage=map["message"] ?: ""
            return map["message"] ?: ""
        } else {
            Log.e("DogImages", "Error getting image: ${response.code}")
            return ""
        }
    }

    fun getImages(context: Context, number: Int): List<String> {
        val url = "https://dog.ceo/api/breeds/image/random/?count=${number}"
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val json = response.body?.string() ?: ""
            val type = object : TypeToken<List<String>>() {}.type
            dogImages=gson.fromJson(json, type)
            firstImage=dogImages[currentIndex]
            return gson.fromJson(json, type)
        } else {
            Log.e("DogImages", "Error getting images: ${response.code}")
            return emptyList()
        }
    }


    fun getFirstImage(): String {
        return firstImage
    }

    fun getNextImage(): String {
        currentIndex++
        return dogImages[currentIndex]
    }

    fun getPreviousImage(): String {
        return dogImages[currentIndex]
    }
}