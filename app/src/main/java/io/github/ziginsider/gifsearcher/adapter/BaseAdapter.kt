package io.github.ziginsider.gifsearcher.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.ziginsider.kotlindiffutil.utils.inflate

/**
 * Created by zigin on 20.01.2018.
 */
abstract class BaseAdapter<T>(protected var itemList: List<T>,
                                  private val layoutResId: Int)
    : RecyclerView.Adapter<BaseAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent inflate layoutResId
        val viewHolder = Holder(view)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BaseAdapter.Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    protected open fun onItemClick(itemView: View, position: Int) {}

    protected open fun View.bind(item: T) {}
}