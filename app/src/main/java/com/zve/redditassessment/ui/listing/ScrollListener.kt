package com.zve.redditassessment.ui.listing

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zve.redditassessment.app.ApplicationConstants
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Peter on 12.01.2018.
 */

class ScrollListener(private val onLoadListener: OnLoadListener) : RecyclerView.OnScrollListener() {

    private val previousTotal: AtomicInteger = AtomicInteger(0)
    private val loading: AtomicBoolean = AtomicBoolean(true)

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (loading.get()) {
            if (totalItemCount > previousTotal.get()) {
                loading.set(false)
                previousTotal.set(totalItemCount)
            }
        }

        val visibleThreshold = ApplicationConstants.VISIBLE_THRESHOLD
        if (!loading.get() && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            onLoadListener.onLoadDone(ApplicationConstants.ITEMS_COUNT)
            loading.set(true)
        }
    }

    interface OnLoadListener {
        fun onLoadDone(amount: Int)
    }
}
