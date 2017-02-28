package com.itis.androidlab.githubmvp.screen.commits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Commit;
import com.itis.androidlab.githubmvp.content.Repository;
import com.itis.androidlab.githubmvp.widget.BaseAdapter;

import java.util.List;

/**
 * @author ilya
 */

public class CommitsAdapter extends BaseAdapter<CommitsHolder, Commit> {

    public CommitsAdapter(@NonNull List<Commit> items) {
        super(items);
    }

    @Override
    public CommitsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commit, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Commit commit = getItem(position);
        holder.bind(commit);
    }
}
