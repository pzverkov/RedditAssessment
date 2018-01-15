package com.zve.redditassessment.ui.listing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zve.redditassessment.R;
import com.zve.redditassessment.models.RedditLink;
import com.zve.redditassessment.models.RedditObject;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by Peter on 11.01.2018.
 */

public class RedditListingAdapter extends RecyclerView.Adapter<RedditListingAdapter.ViewHolder> {
    private List<RedditObject> links = new ArrayList<RedditObject>();
    private OnClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout wrapper;
        ImageView thumbnail;
        TextView title;

        public ViewHolder(View v) {
            super(v);
            wrapper = (RelativeLayout) v.findViewById(R.id.wrapper);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            title = (TextView) v.findViewById(R.id.title);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RedditLink link = (RedditLink) links.get(position);

        Picasso.with(holder.thumbnail.getContext())
                .load(link.getThumbnail())
                .resize(100, 100)
                .error(R.drawable.reddit)
                .centerCrop()
                .into(holder.thumbnail);

        holder.title.setText(link.getTitle());
        holder.wrapper.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onClick(holder.thumbnail, link.getSubreddit(), link.getId());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    @DebugLog
    public void setItems(List<RedditObject> items) {
        links.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
         clickListener = listener;
    }

    public interface OnClickListener {
        void onClick(View imageView, String subreddit, String id);
    }
}
