package com.getimage.library.avdhesh

import com.google.gson.annotations.SerializedName


class Image {

    @SerializedName("status")
    var status: String = ""

    @SerializedName("message")
    var message: String = ""

}

class ImageList {

    @SerializedName("status")
    var status: String = ""

    @SerializedName("message")
    var message: List<String> = emptyList()

}