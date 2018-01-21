package io.github.ziginsider.gifsearcher.adapter

import android.support.v7.util.DiffUtil
import android.view.View
import io.github.ziginsider.gifsearcher.R
import io.github.ziginsider.gifsearcher.model.Gif
import android.text.TextUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gif_item.view.*



/**
 * Created by zigin on 20.01.2018.
 */

class gifAdapter(gifs: List<Gif>,
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

        Glide.with(gifItem.context)
                .asGif()
                .load(item.images.fixed_width.url)
                .into(gifItem)
    }



}