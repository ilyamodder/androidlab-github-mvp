package com.itis.androidlab.githubmvp.screen.commits;

import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Commit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ilya
 */

public class CommitsHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.commitMessage)
    TextView mCommitMessage;

    @BindView(R.id.commitAuthor)
    TextView mCommitAuthor;

    public CommitsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Commit commit) {
        mCommitMessage.setText(commit.getMessage());
        mCommitAuthor.setText(commit.getAuthor().getAuthorName());
    }
}
