package io.github.ziginsider.gifsearcher

import android.app.SearchManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import io.github.ziginsider.gifsearcher.adapter.EndlessScrollListener
import io.github.ziginsider.gifsearcher.adapter.GifAdapter
import io.github.ziginsider.gifsearcher.model.Gif
import io.github.ziginsider.gifsearcher.retrofit.API_KEY
import io.github.ziginsider.gifsearcher.retrofit.LIMIT_SEARCH_QUERY
import io.github.ziginsider.gifsearcher.retrofit.RetrofitService
import io.github.ziginsider.gifsearcher.utils.isPortrait
import io.github.ziginsider.gifsearcher.utils.toast
import io.github.ziginsider.gifsearcher.viewmodel.GifViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var recyclerAdapter: GifAdapter? = null
    private var offset = 0
    private var mquery = ""
    private var isSearch = false
    private var gifViewModel: GifViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewInit()

        gifViewModel = ViewModelProviders.of(this).get(GifViewModel::class.java)

        if (gifViewModel!!.loadedGifs != null) updateAdapter(gifViewModel!!.loadedGifs!!)
        else getTrending(offset)
    }

    private fun updateAdapter(gifsList: List<Gif>) {
        recyclerAdapter?.update(gifsList) ?: setUpRecyclerView(gifsList)
    }

    private fun addAdapter(gifsList: List<Gif>) {
        recyclerAdapter?.add(gifsList) ?: setUpRecyclerView(gifsList)
    }

    private fun setUpRecyclerView(gifsList: List<Gif>) {
        recyclerAdapter = GifAdapter(gifsList,
                { toast ("My URL = ${images.fixed_width.url}")})
        with(recyclerView) {
            layoutManager = GridLayoutManager(this.context, if (isPortrait()) 3 else 4)
            setHasFixedSize(true)
            adapter = recyclerAdapter
            addOnScrollListener(EndlessScrollListener({pagingGifs()}, layoutManager as GridLayoutManager))
        }
    }

    private fun pagingGifs() {
        offset += LIMIT_SEARCH_QUERY
        if (isSearch) getGifs(mquery, offset)
        else getTrending(offset)
    }

    private fun addGifs(gifsList: List<Gif>) {
        if (offset == 0) updateAdapter(gifsList)
        else {
            addAdapter(gifsList)
        }
        gifViewModel?.loadedGifs = recyclerAdapter?.getItems()
    }

    private fun searchViewInit() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                offset = 0
                isSearch = true
                mquery = query
                getGifs(query, offset)
                return false
        }

            override fun onQueryTextChange(newText: String): Boolean {
                //offset = 0
                return false
            }
        })
    }

    private fun getGifs(query: String, offset: Int) {
        progressBar.visibility = View.VISIBLE

        RetrofitService.create()
                .getSearchGifsRx(query, LIMIT_SEARCH_QUERY, offset, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    response -> addGifs(response.data)
                    progressBar.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun getTrending(offset: Int) {
        progressBar.visibility = View.VISIBLE

        RetrofitService.create()
                .getTrendingGifsRx(LIMIT_SEARCH_QUERY, offset, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    response -> addGifs(response.data)
                    progressBar.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                })
    }


}

