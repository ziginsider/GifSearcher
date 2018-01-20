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


//    companion object Factory {
//        fun create(): RetrofitService {
//            val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl("http://api.giphy.com/")
//                    .build()
//
//            return retrofit.create(RetrofitService::class.java)
//        }
//    }

    val API_BASE_URL: String get() = "http://api.giphy.com"
    val API_KEY: String get() = "edloSyPsEKlbTvQrW2E7XB3tS3XPs0DC"

    @GET("/v1/gifs/trending?api_key=edloSyPsEKlbTvQrW2E7XB3tS3XPs0DC")
    abstract fun getTrendingGifs(): Observable<ResponseData>

    @GET("/v1/gifs/search")
    abstract fun getSearchgifs(@Query("q") search: String,
                                 @Query("limit") limit: Int,
                                 @Query("api_key") key: String): Observable<ResponseData>


}