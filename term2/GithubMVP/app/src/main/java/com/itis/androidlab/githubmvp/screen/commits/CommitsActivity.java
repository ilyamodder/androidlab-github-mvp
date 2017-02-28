package com.itis.androidlab.githubmvp.screen.commits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Commit;
import com.itis.androidlab.githubmvp.content.Repository;
import com.itis.androidlab.githubmvp.screen.general.LoadingDialog;
import com.itis.androidlab.githubmvp.screen.general.LoadingView;
import com.itis.androidlab.githubmvp.screen.repositories.RepositoriesAdapter;
import com.itis.androidlab.githubmvp.screen.repositories.RepositoriesPresenter;
import com.itis.androidlab.githubmvp.widget.DividerItemDecoration;
import com.itis.androidlab.githubmvp.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class CommitsActivity extends AppCompatActivity implements CommitsView {

    private static final String REPO_NAME_KEY = "repo_name_key";

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindView(R.id.recyclerView) EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty) View mEmptyView;

    private LoadingView mLoadingView;

    private CommitsPresenter mPresenter;

    private CommitsAdapter mAdapter;

    public static void start(@NonNull Activity activity, @NonNull Repository repository) {
        Intent intent = new Intent(activity, CommitsActivity.class);
        intent.putExtra(REPO_NAME_KEY, repository.getName());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new CommitsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        String repositoryName = getIntent().getStringExtra(REPO_NAME_KEY);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new CommitsPresenter(lifecycleHandler, this);
        mPresenter.init(repositoryName);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showCommits(List<Commit> commits) {
        mAdapter.changeDataSet(commits);
    }

    @Override
    public void showError() {
        mAdapter.clear();
    }
}
