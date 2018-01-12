package com.zve.redditassessment.ui.listing;

import com.zve.redditassessment.api.RedditService;
import com.zve.redditassessment.models.RedditListing;
import com.zve.redditassessment.models.RedditResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Peter on 11.01.2018.
 */

public class ListingPresenterImpl implements ListingPresenter {
    private ListingView mView;

    private RedditService mRedditService;

    public ListingPresenterImpl(RedditService redditService) {
        mRedditService = redditService;
    }

    @Override
    public void setView(ListingView view) {
        mView = view;
    }

    @Override
    public void loadItems(int count, String after) {
        if (mView != null && after.isEmpty()) {
            mView.showLoading();
        }

        Call<RedditResponse<RedditListing>> call = mRedditService.getTopListing(count, after);
        call.enqueue(new Callback<RedditResponse<RedditListing>>() {
            @Override
            public void onResponse(Call<RedditResponse<RedditListing>> call, Response<RedditResponse<RedditListing>> response) {
                if (mView != null) {
                    mView.hideLoading();

                    RedditListing listing = response.body().getData();

                    mView.showListing(listing.getChildren());
                    mView.setAfter(listing.getAfter());
                }
            }

            @Override
            public void onFailure(Call<RedditResponse<RedditListing>> call, Throwable t) {
                if (mView != null) {
                    mView.hideLoading();
                    mView.showErrorMessage();
                }
            }
        });
    }

    @Override
    public void stop() {
        mView = null;
    }
}
