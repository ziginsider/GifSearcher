package io.github.ziginsider.gifsearcher

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import io.github.ziginsider.gifsearcher.adapter.GifAdapter
import io.github.ziginsider.gifsearcher.model.Gif
import io.github.ziginsider.gifsearcher.model.SearchData
import io.github.ziginsider.gifsearcher.retrofit.API_KEY
import io.github.ziginsider.gifsearcher.retrofit.LIMIT_SEARCH_QUERY
import io.github.ziginsider.gifsearcher.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private var recyclerAdapter: GifAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        searchViewInit()


    }

    private fun updateAdapter(gifsList: List<Gif>) {
        recyclerAdapter?.update(gifsList) ?: setUpRecyclerView(gifsList)
    }

    private fun setUpRecyclerView(gifsList: List<Gif>) {
        recyclerAdapter = GifAdapter(gifsList, {})
        with(recyclerView) {
            Log.d("TAG", gifsList.toString())
            layoutManager = GridLayoutManager(this.context, 3)
            //setHasFixedSize(true)
            adapter = recyclerAdapter
            //scheduleLayoutAnimation()
        }
    }

    private fun searchViewInit() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                //toast("I'm toast message! $query")
                progressBar.visibility = View.VISIBLE
                getGifs(query)
                return false
        }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    //TODO RX
//    private fun searchGiphyQuery(query: String) {
//        service.getSearchgifs(query, LIMIT_SEARCH_QUERY, API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Consumer<SearchData>() {
//                    fun call(responseData: SearchData) {
//
//                        progressBar.visibility = View.GONE
//                        val temp = getGiphyObjectsOutOfResponse(responseData)
//                        Collections.reverse(temp)
//                        for (temData in temp) {
//                            giphyList.add(0, temData)
//                        }
//                        recyclerAdapter.update(giphyList)
//
//                    }
//                })
//    }

    private fun getGifs(query: String) {
        RetrofitService.create().getSearchGifs(query, LIMIT_SEARCH_QUERY, API_KEY).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                updateAdapter(response.body()!!.data)
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable?) {
            }
        })
    }
}

