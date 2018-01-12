package com.zve.redditassessment.ui.listing

/**
 * Created by Peter on 11.01.2018.
 */

interface ListingPresenter {
    fun setView(view: ListingView)
    fun stop()
    fun loadItems(count: Int, after: String)
}
