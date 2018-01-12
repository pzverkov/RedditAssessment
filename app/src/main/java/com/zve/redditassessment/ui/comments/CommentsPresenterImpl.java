package com.zve.redditassessment.ui.comments;

import com.zve.redditassessment.api.RedditService;
import com.zve.redditassessment.models.RedditListing;
import com.zve.redditassessment.models.RedditResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Peter on 11.01.2018.
 */

public class CommentsPresenterImpl implements CommentsPresenter {
    private CommentsView commentsView;
    private RedditService redditService;

    public CommentsPresenterImpl(RedditService redditService) {
        this.redditService = redditService;
    }

    @Override
    public void setView(CommentsView view) {
        commentsView = view;
    }

    @Override
    public void loadComments(String subreddit, String id) {
        if (commentsView != null) {
            commentsView.showLoading();
        }

        Call<List<RedditResponse<RedditListing>>> call = redditService.getComments(subreddit, id);
        call.enqueue(new Callback<List<RedditResponse<RedditListing>>>() {
            @Override
            public void onResponse(Call<List<RedditResponse<RedditListing>>> call, Response<List<RedditResponse<RedditListing>>> response) {
                if (commentsView != null) {
                    commentsView.hideLoading();
                    commentsView.showComments(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RedditResponse<RedditListing>>> call, Throwable t) {
                if (commentsView != null) {
                    commentsView.hideLoading();
                    commentsView.showErrorMessage();
                }
            }
        });
    }

    @Override
    public void stop() {
        commentsView = null;
    }
}
