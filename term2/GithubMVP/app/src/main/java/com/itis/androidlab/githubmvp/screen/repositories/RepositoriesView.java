package com.itis.androidlab.githubmvp.screen.repositories;

import android.support.annotation.NonNull;

import com.itis.androidlab.githubmvp.content.Repository;
import com.itis.androidlab.githubmvp.screen.general.LoadingView;

import java.util.List;

public interface RepositoriesView extends LoadingView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showCommits(@NonNull Repository repository);

    void showError();
}
