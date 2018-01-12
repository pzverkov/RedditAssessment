package com.zve.redditassessment.ui.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.zve.redditassessment.R;
import com.zve.redditassessment.app.RedditApplication;
import com.zve.redditassessment.models.RedditLink;
import com.zve.redditassessment.models.RedditListing;
import com.zve.redditassessment.models.RedditResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Peter on 11.01.2018.
 */

public class CommentsActivity extends AppCompatActivity implements CommentsView {

    public static final String SUBREDDIT = "subreddit";
    public static final String TAG_ID = "id";

    @Inject
    CommentsPresenter commentsPresenter;
    RedditCommentsAdapter commentsAdapter;
    @BindView(R.id.thumbnailImage)
    ImageView imageView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.commentsList)
    RecyclerView commentsList;
    @BindView(R.id.titleText)
    TextView titleTextView;

    private String subreddit;
    private String id;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SUBREDDIT, subreddit);
        outState.putString(TAG_ID, id);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subreddit = savedInstanceState == null ?
                getIntent().getStringExtra(SUBREDDIT) : savedInstanceState.getString(SUBREDDIT);
        id = savedInstanceState == null ? getIntent().getStringExtra(TAG_ID) :
                savedInstanceState.getString(TAG_ID);

        setContentView(R.layout.activity_comments);
        RedditApplication.getInstance().getAppComponent().inject(this);
        ButterKnife.bind(this);

        commentsAdapter = new RedditCommentsAdapter();
        commentsList.setLayoutManager(new LinearLayoutManager(this));
        commentsList.setHasFixedSize(true);
        commentsList.setAdapter(commentsAdapter);

        commentsPresenter.setView(this);
        commentsPresenter.loadComments(subreddit, id);
    }

    @Override
    protected void onStop() {
        super.onStop();
        commentsPresenter.stop();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<RedditResponse<RedditListing>> redditResponse) {
        RedditLink link = (RedditLink) redditResponse.get(0).getData().getChildren().get(0);

        if (!link.getThumbnail().isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load(link.getThumbnail())
                    .resize(100, 100)
                    .centerCrop()
                    .into(imageView);
        } else { imageView.setVisibility(View.GONE); }

        titleTextView.setText(link.getTitle());

        commentsAdapter.setItems(redditResponse.get(1).getData().getChildren());
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "There was a problem loading the comments", Toast.LENGTH_LONG).show();
        finish();
    }
}
