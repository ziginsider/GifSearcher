package io.github.ziginsider.gifsearcher.retrofit

import io.github.ziginsider.gifsearcher.model.SearchData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by zigin on 20.01.2018.
 */
interface RetrofitService {

    //fun getSearchGif() : Observable<SearchData>
    @GET("gifs/search")
    fun searchGifs(@Query("q") query: String,
                   @Query("limit") limit: Int,
                   @Query("api_key") api_key: String): Observable<SearchData>

    companion object Factory {
        fun create(): RetrofitService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.giphy.com/v1/")
                    .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}