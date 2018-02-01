package io.github.ziginsider.gifsearcher.binding

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by zigin on 01.02.2018.
 */


@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
