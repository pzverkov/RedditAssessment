package com.zve.redditassessment.ui.listing;

import com.zve.redditassessment.models.RedditObject;

import java.util.List;

/**
 * Created by Peter on 11.01.2018.
 */

public interface ListingView {
    void showLoading();
    void hideLoading();
    void showListing(List<RedditObject> items);
    void showErrorMessage();
    void setAfter(String after);
}
