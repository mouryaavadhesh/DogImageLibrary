package com.getimage.library.avdhesh

import com.google.gson.annotations.SerializedName


class Image {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

}

class ImageList {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: List<String> = ArrayList<String>()

}