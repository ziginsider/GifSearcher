package io.github.ziginsider.gifsearcher.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }


    @GET("/v1/gifs/trending")
    fun getTrendingGifs(@Query("limit") limit: Int,
                        @Query("api_key") key: String): Call<SearchData>

    @GET("/v1/gifs/search")
    fun getSearchGifs(@Query("q") search: String,
                      @Query("limit") limit: Int,
                      @Query("api_key") key: String): Call<SearchData>


    @GET("/v1/gifs/trending")
    fun getTrendingGifsRx(@Query("limit") limit: Int,
                          @Query("api_key") key: String): Observable<SearchData>

    @GET("/v1/gifs/search")
    fun getSearchGifsRx(@Query("q") search: String,
                        @Query("limit") limit: Int,
                        @Query("api_key") key: String): Observable<SearchData>


}