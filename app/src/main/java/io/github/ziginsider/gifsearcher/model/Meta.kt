package io.github.ziginsider.gifsearcher.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zigin on 20.01.2018.
 */
class Meta(@SerializedName("status")
           val status: Int,

           @SerializedName("msg")
           val msg: String,

           @SerializedName("response_id")
           val response_id: String)