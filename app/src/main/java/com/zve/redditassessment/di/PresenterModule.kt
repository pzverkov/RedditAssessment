package com.zve.redditassessment.di

import com.zve.redditassessment.api.RedditService
import com.zve.redditassessment.ui.comments.CommentsPresenter
import com.zve.redditassessment.ui.comments.CommentsPresenterImpl
import com.zve.redditassessment.ui.listing.ListingPresenter
import com.zve.redditassessment.ui.listing.ListingPresenterImpl

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by Peter on 11.01.2018.
 */

@Module(includes = arrayOf(NetworkModule::class))
class PresenterModule {
    @Provides
    @Singleton
    internal fun provideListingPresenter(redditService: RedditService): ListingPresenter {
        return ListingPresenterImpl(redditService)
    }

    @Provides
    @Singleton
    internal fun provideCommentsPresenter(redditService: RedditService): CommentsPresenter {
        return CommentsPresenterImpl(redditService)
    }
}
