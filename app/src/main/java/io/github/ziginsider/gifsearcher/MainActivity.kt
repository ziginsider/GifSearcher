package io.github.ziginsider.gifsearcher

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import io.github.ziginsider.gifsearcher.adapter.gifAdapter
import io.github.ziginsider.gifsearcher.model.Gif
import io.github.ziginsider.gifsearcher.retrofit.API_KEY
import io.github.ziginsider.gifsearcher.retrofit.LIMIT_SEARCH_QUERY
import io.github.ziginsider.gifsearcher.retrofit.RetrofitService
import io.github.ziginsider.gifsearcher.utils.isPortrait
import io.github.ziginsider.gifsearcher.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var recyclerAdapter: gifAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewInit()

        getTrending()

    }

    private fun updateAdapter(gifsList: List<Gif>) {
        recyclerAdapter?.update(gifsList) ?: setUpRecyclerView(gifsList)
    }

    private fun setUpRecyclerView(gifsList: List<Gif>) {
        recyclerAdapter = gifAdapter(gifsList,
                { toast ("My URL = ${images.fixed_width.url}")})
        with(recyclerView) {
            Log.d("TAG", gifsList.toString())
            layoutManager = GridLayoutManager(this.context, if (isPortrait()) 3 else 4)
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
                getGifs(query)
                return false
        }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun getGifs(query: String) {
        progressBar.visibility = View.VISIBLE

        RetrofitService.create()
                .getSearchGifsRx(query, LIMIT_SEARCH_QUERY, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    response -> updateAdapter(response.data)
                    progressBar.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun getTrending() {
        progressBar.visibility = View.VISIBLE

        RetrofitService.create()
                .getTrendingGifsRx(LIMIT_SEARCH_QUERY, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    response -> updateAdapter(response.data)
                    progressBar.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                })
    }
}

