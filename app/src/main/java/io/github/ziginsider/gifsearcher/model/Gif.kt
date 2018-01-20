package io.github.ziginsider.gifsearcher.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zigin on 20.01.2018.
 */

class Gif(
        @SerializedName("id")
        val id: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("images")
        val images: ImageSet)