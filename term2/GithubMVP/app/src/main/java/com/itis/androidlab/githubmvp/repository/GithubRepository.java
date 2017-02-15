package com.itis.androidlab.githubmvp.repository;

import android.support.annotation.NonNull;

import com.itis.androidlab.githubmvp.content.Authorization;
import com.itis.androidlab.githubmvp.content.Repository;

import java.util.List;

import rx.Observable;


public interface GithubRepository {

    @NonNull
    Observable<List<Repository>> repositories();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);
}
