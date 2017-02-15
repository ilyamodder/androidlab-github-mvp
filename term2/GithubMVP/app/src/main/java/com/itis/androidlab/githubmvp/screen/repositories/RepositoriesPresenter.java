package com.itis.androidlab.githubmvp.screen.repositories;

import android.support.annotation.NonNull;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Repository;
import com.itis.androidlab.githubmvp.repository.RepositoryProvider;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class RepositoriesPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final RepositoriesView mView;

    public RepositoriesPresenter(@NonNull LifecycleHandler lifecycleHandler,
                                 @NonNull RepositoriesView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideGithubRepository()
                .repositories()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showRepositories, throwable -> mView.showError());
    }

    public void onItemClick(@NonNull Repository repository) {
        mView.showCommits(repository);
    }
}
