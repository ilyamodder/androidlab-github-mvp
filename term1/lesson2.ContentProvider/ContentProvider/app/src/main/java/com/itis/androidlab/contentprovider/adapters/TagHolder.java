package com.itis.androidlab.contentprovider.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itis.androidlab.contentprovider.R;
import com.itis.androidlab.contentprovider.models.Tag;

public class TagHolder extends RecyclerView.ViewHolder {

    private TextView tagTextView;

    public TagHolder(View itemView) {
        super(itemView);
        tagTextView = (TextView) itemView;
    }

    public static TagHolder buildForParent(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_tag, parent, false);
        return new TagHolder(view);
    }

    public void bindView(@NonNull Tag tag) {
        tagTextView.setText(String.format("#%s", tag.getName()));
    }
}