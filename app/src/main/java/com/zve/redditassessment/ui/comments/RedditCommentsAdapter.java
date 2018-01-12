package com.zve.redditassessment.ui.comments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zve.redditassessment.R;
import com.zve.redditassessment.models.RedditComment;
import com.zve.redditassessment.models.RedditObject;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 11.01.2018.
 */

public class RedditCommentsAdapter extends RecyclerView.Adapter<RedditCommentsAdapter.ViewHolder> {
    List<RedditObject> redditComments = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView date;
        TextView comment;

        public ViewHolder(View v) {
            super(v);

            author = (TextView) v.findViewById(R.id.author);
            date = (TextView) v.findViewById(R.id.date);
            comment = (TextView) v.findViewById(R.id.comment);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RedditObject comment = redditComments.get(position);

        if (comment.getClass().getSimpleName().equals("RedditComment")) {
            holder.author.setText(((RedditComment) comment).getAuthor());
            holder.date.setText(formatDate(((RedditComment) comment).getCreatedUtc()));
            holder.comment.setText(((RedditComment) comment).getBody());
        }
    }

    @Override
    public int getItemCount() {
        return redditComments.size();
    }

    public void setItems(List<RedditObject> items) {
        redditComments.addAll(items);
        notifyDataSetChanged();
    }

    private String formatDate(DateTime date) {
        return new PeriodFormatterBuilder()
                .printZeroNever()
                .toFormatter().print(new Period(date, new DateTime()));
    }
}
