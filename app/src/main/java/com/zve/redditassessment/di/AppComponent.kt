package com.zve.redditassessment.di

import com.zve.redditassessment.ui.comments.CommentsActivity
import com.zve.redditassessment.ui.listing.ListingActivity

import javax.inject.Singleton

import dagger.Component

/**
 * Created by Peter on 11.01.2018.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, PresenterModule::class, NetworkModule::class))
interface AppComponent {
    fun inject(target: ListingActivity)
    fun inject(target: CommentsActivity)
}
