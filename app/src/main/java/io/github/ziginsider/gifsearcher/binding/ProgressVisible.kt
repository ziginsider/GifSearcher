package io.github.ziginsider.gifsearcher.binding

/**
 * Created by zigin on 01.02.2018.
 */

data class ProgressVisible(var visible: Boolean = false) {
    fun getV(): Boolean {
        return visible
    }
}