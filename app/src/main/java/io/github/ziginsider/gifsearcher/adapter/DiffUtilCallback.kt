package io.github.ziginsider.gifsearcher.adapter

import android.support.v7.util.DiffUtil
import io.github.ziginsider.gifsearcher.model.Gif

/**
 * Created by zigin on 20.01.2018.
 */

class DiffUtilCallback(private val oldItems: List<Gif>,
                       private val newItems: List<Gif>): DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition].id  == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]
}