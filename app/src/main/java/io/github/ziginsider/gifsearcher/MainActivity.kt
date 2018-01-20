package io.github.ziginsider.gifsearcher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.ziginsider.gifsearcher.adapter.GifAdapter
import io.github.ziginsider.gifsearcher.model.Gif
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var recyclerAdapter: GifAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
