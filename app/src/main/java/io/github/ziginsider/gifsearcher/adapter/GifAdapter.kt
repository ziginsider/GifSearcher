package io.github.ziginsider.gifsearcher.adapter

import android.support.v7.util.DiffUtil
import android.view.View
import io.github.ziginsider.gifsearcher.R
import io.github.ziginsider.gifsearcher.model.Gif

/**
 * Created by zigin on 20.01.2018.
 */

class GifAdapter(gifs: List<Gif>,
                 private var itemClick: Gif.() -> Unit = {})
    : BaseAdapter<Gif>(gifs, R.layout.gif_item) {

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }

    fun update(newItemList: List<Gif>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(itemList, newItemList))
        itemList = newItemList
        result.dispatchUpdatesTo(this)
    }

    override fun View.bind(item: Gif) {
        //TODO bind

    }
}