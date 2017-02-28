package com.itis.androidlab.githubmvp.repository;

import android.support.annotation.NonNull;

import com.itis.androidlab.githubmvp.api.ApiFactory;
import com.itis.androidlab.githubmvp.content.Authorization;
import com.itis.androidlab.githubmvp.content.Commit;
import com.itis.androidlab.githubmvp.content.CommitResponse;
import com.itis.androidlab.githubmvp.content.Repository;
import com.itis.androidlab.githubmvp.utils.AuthorizationUtils;
import com.itis.androidlab.githubmvp.utils.PreferenceUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class DefaultGithubRepository implements GithubRepository {

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return ApiFactory.getGithubService()
                .repositories()
                .flatMap(repositories -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Repository.class);
                        realm.insert(repositories);
                    });
                    return Observable.just(repositories);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Repository> repositories = realm.where(Repository.class).findAll();
                    return Observable.just(realm.copyFromRealm(repositories));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        String authorizationString = AuthorizationUtils.createAuthorizationString(login, password);
        return ApiFactory.getGithubService()
                .authorize(authorizationString, AuthorizationUtils.createAuthorizationParam())
                .flatMap(authorization -> {
                    PreferenceUtils.saveToken(authorization.getToken());
                    PreferenceUtils.saveUserName(login);
                    ApiFactory.recreate();
                    return Observable.just(authorization);
                })
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<Commit>> getCommits(@NonNull String owner, @NonNull String repoName) {
        return ApiFactory.getGithubService().commits(owner, repoName)
                .flatMap(Observable::from)
                .map(CommitResponse::getCommit)
                .toList()
                .map(commits -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Commit.class);
                        realm.insert(commits);
                    });
                    return commits;
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Commit> commits = realm.where(Commit.class).findAll();
                    return Observable.just(realm.copyFromRealm(commits));
                })
                .compose(RxUtils.async());
    }
}
