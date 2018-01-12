package com.zve.redditassessment.ui.comments;

import com.zve.redditassessment.models.RedditListing;
import com.zve.redditassessment.models.RedditResponse;

import java.util.List;

/**
 * Created by Peter on 11.01.2018.
 */

public interface CommentsView {
    void showLoading();
    void hideLoading();
    void showErrorMessage();
    void showComments(List<RedditResponse<RedditListing>> redditResponse);
}
