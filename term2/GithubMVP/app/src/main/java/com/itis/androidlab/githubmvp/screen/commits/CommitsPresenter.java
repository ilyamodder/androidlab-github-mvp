package com.itis.androidlab.githubmvp.screen.commits;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.repository.RepositoryProvider;
import com.itis.androidlab.githubmvp.utils.PreferenceUtils;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * @author ilya
 */

public class CommitsPresenter {
    private CommitsView mView;
    private LifecycleHandler mLifecycleHandler;

    public CommitsPresenter(LifecycleHandler lifecycleHandler, CommitsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(String repoName) {
        PreferenceUtils.getUserName()
                .flatMap(user ->
                        RepositoryProvider.provideGithubRepository().getCommits(user, repoName))
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showCommits, throwable -> mView.showError());
    }
}
