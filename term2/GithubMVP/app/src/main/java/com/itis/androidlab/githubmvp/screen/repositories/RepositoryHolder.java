package com.itis.androidlab.githubmvp.screen.repositories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repositoryName) TextView mName;

    @BindView(R.id.repositoryLanguage) TextView mLanguage;

    public RepositoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Repository repository) {
        mName.setText(repository.getName());
        mLanguage.setText(repository.getLanguage());
    }
}
