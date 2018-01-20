package io.github.ziginsider.gifsearcher.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zigin on 20.01.2018.
 */

class Pagination(@SerializedName("total_count")
                 val total_count: Int,

                 @SerializedName("count")
                 val count: Int,

                 @SerializedName("offset")
                 val offset: Int
)
