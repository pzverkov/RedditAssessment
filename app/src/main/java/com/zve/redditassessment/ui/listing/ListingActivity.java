package com.zve.redditassessment.ui.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zve.redditassessment.R;
import com.zve.redditassessment.app.RedditApplication;
import com.zve.redditassessment.models.RedditObject;
import com.zve.redditassessment.ui.comments.CommentsActivity;
import com.zve.redditassessment.app.ApplicationConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Peter on 11.01.2018.
 */

public class ListingActivity extends AppCompatActivity implements ListingView, RedditListingAdapter.OnClickListener {

    public static final String TAG_AFTER = "after";
    @Inject
    ListingPresenter listingPresenter;

    private String afterText;
    private RedditListingAdapter adapter;

    @BindView(R.id.listingView)
    RecyclerView listingView;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TAG_AFTER, afterText);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        RedditApplication.getInstance().getAppComponent().inject(this);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            afterText = "";
        } else {
            afterText = savedInstanceState.getString(TAG_AFTER);
        }

        adapter = new RedditListingAdapter();
        adapter.setOnClickListener(this);
        listingView.setLayoutManager(new LinearLayoutManager(this));
        listingView.setHasFixedSize(true);
        listingView.setAdapter(adapter);
        listingView.addOnScrollListener(new ScrollListener(amount -> loadItems(amount, afterText)));
        listingPresenter.setView(this);
        loadItems(ApplicationConstants.INSTANCE.getITEMS_COUNT(), afterText);
    }

    @Override
    public void onClick(View imageView, String subreddit, String id) {

        Intent detailsIntent = new Intent(this, CommentsActivity.class);
        detailsIntent.putExtra(ApplicationConstants.INSTANCE.getSUBREDDIT(), subreddit);
        detailsIntent.putExtra(ApplicationConstants.INSTANCE.getID(), id);

        //Adding shared element transition animation details
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(ListingActivity.this, imageView,
                        ApplicationConstants.INSTANCE.getSHARED_ELEMENT_NAME());

        startActivity(detailsIntent, options.toBundle());
    }

    @Override
    protected void onStop() {
        super.onStop();
        listingPresenter.stop();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showListing(List<RedditObject> items) {
        adapter.setItems(items);
    }

    @Override
    public void setAfter(String after) {
        afterText = after;
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "There was a problem loading the listing", Toast.LENGTH_LONG).show();
    }

    private void loadItems(int count, String after) {
        if (listingPresenter != null) {
            listingPresenter.loadItems(count, after);
        }
    }
}