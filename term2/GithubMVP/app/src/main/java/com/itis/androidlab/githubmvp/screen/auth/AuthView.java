package com.itis.androidlab.githubmvp.screen.auth;

import com.itis.androidlab.githubmvp.screen.general.LoadingView;


public interface AuthView extends LoadingView {

    void openRepositoriesScreen();

    void showLoginError();

    void showPasswordError();

}