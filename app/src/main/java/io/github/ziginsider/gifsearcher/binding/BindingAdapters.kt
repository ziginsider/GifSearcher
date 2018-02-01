package io.github.ziginsider.gifsearcher.binding

import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.view.View

/**
 * Created by zigin on 01.02.2018.
 */

//view visible or gone
//object BindingAdapters {

    @set:BindingAdapter("visibleOrGone")
    var View.visibleOrGone
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }

//    class Component: DataBindingComponent {
//        override fun getBindingAdapters(): BindingAdapters {
//            return BindingAdapters
//        }
//    }
//}