package com.itis.androidlab.contentprovider.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.itis.androidlab.contentprovider.models.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagHolder> {

    private List<Tag> mTags = new ArrayList<>();

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TagHolder.buildForParent(parent);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        holder.bindView(mTags.get(position));
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public void setTags(List<Tag> tags) {
        this.mTags = Collections.unmodifiableList(tags);
        notifyDataSetChanged();
    }
}