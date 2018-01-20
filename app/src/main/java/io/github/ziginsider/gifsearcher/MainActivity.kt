package io.github.ziginsider.gifsearcher

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import io.github.ziginsider.gifsearcher.adapter.GifAdapter
import io.github.ziginsider.gifsearcher.model.Gif
import io.github.ziginsider.gifsearcher.retrofit.API_KEY
import io.github.ziginsider.gifsearcher.retrofit.LIMIT_SEARCH_QUERY
import io.github.ziginsider.gifsearcher.retrofit.RetrofitService
import io.github.ziginsider.kotlindiffutil.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.net.CacheResponse
import java.util.*

class MainActivity : AppCompatActivity() {


    private var recyclerAdapter: GifAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewInit()


    }

    private fun updateAdapter(gifsList: List<Gif>) {
        recyclerAdapter?.update(gifsList) ?: setUpRecyclerView(gifsList)
    }

    private fun setUpRecyclerView(userList: List<Gif>) {
        recyclerAdapter = GifAdapter(userList, {})
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this.context)
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
                toast("I'm toast message! $query")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun searchGiphyQuery(query: String) {
        service.getSearchgifs(query, LIMIT_SEARCH_QUERY, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<ResponseData>() {
                    fun call(responseData: ResponseData) {

                        progressBar.visibility = View.GONE
                        val temp = getGiphyObjectsOutOfResponse(responseData)
                        Collections.reverse(temp)
                        for (temData in temp) {
                            giphyList.add(0, temData)
                        }
                        adapter.setNewData(giphyList)

                    }
                })
                .subscribe(object : Action1<ResponseData>() {
                    fun call(responseData: ResponseData) {
                        progressBar.visibility = View.GONE
                        val temp = getGiphyObjectsOutOfResponse(responseData)
                        Collections.reverse(temp)
                        for (temData in temp) {
                            giphyList.add(0, temData)
                        }
                        adapter.setNewData(giphyList)
                    }
                })
    }


}

