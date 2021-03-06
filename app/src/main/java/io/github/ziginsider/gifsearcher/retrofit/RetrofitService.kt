package io.github.ziginsider.gifsearcher.retrofit

import io.github.ziginsider.gifsearcher.model.SearchData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by zigin on 20.01.2018.
 */
interface RetrofitService {

    companion object {
        fun create(): RetrofitService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.giphy.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }


    @GET("/v1/gifs/trending")
    abstract fun getTrendingGifs(@Query("limit") limit: Int,
                                 @Query("api_key") key: String): Call<SearchData>//Observable<SearchData>

    @GET("/v1/gifs/search")
    abstract fun getSearchGifs(@Query("q") search: String,
                                 @Query("limit") limit: Int,
                                 @Query("api_key") key: String): Call<SearchData>//Observable<SearchData>


}