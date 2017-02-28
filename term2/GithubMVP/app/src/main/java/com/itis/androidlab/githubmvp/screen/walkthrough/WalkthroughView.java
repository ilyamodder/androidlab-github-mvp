package com.itis.androidlab.githubmvp.screen.walkthrough;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.itis.androidlab.githubmvp.content.Benefit;

import java.util.List;

/**
 * @author ilya
 */

public interface WalkthroughView {
    void showBenefit(int page);

    void showLoginScreen();

    void setBenefits(@NonNull List<Benefit> benefits);

    void setButtonText(@StringRes int textId);
}
