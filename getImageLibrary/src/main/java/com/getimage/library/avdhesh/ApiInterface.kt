package com.getimage.library.avdhesh

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface APIInterface {

    @GET("random")
    fun doGetImage(): Call<Image>

    @GET("random/{number}")
    fun doGetImages(@Path("number")  number:Int): Call<ImageList>

}