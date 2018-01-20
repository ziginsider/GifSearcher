package io.github.ziginsider.gifsearcher.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zigin on 20.01.2018.
 */
class SearchData(@SerializedName("data")
                 val data: List<Gif>,

                 @SerializedName("meta")
                 val meta: Meta,

                 @SerializedName("pagination")
                 val pagination: Pagination)