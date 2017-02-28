package com.itis.androidlab.githubmvp.screen.walkthrough;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Benefit;
import com.itis.androidlab.githubmvp.screen.auth.AuthActivity;
import com.itis.androidlab.githubmvp.utils.PreferenceUtils;
import com.itis.androidlab.githubmvp.widget.PageChangeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WalkthroughActivity extends AppCompatActivity implements
        PageChangeViewPager.PagerStateListener, WalkthroughView {

    @BindView(R.id.pager) PageChangeViewPager mPager;

    @BindView(R.id.btn_walkthrough) Button mActionButton;

    private WalkthroughPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        ButterKnife.bind(this);

        mPager.setOnPageChangedListener(this);

        mPresenter = new WalkthroughPresenter(this);
        mPresenter.init();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_walkthrough)
    public void onActionButtonClick() {
        mPresenter.onButtonClick();
    }

    @Override
    public void onPageChanged(int selectedPage, boolean fromUser) {
        mPresenter.onPageChanged(selectedPage, fromUser);
    }

    @Override
    public void showBenefit(int page) {
        mPager.setCurrentItem(page, true);
    }

    @Override
    public void showLoginScreen() {
        AuthActivity.start(this);
        finish();
    }

    @Override
    public void setBenefits(@NonNull List<Benefit> benefits) {
        mPager.setAdapter(new WalkthroughAdapter(getFragmentManager(), benefits));
    }

    @Override
    public void setButtonText(@StringRes int textId) {
        mActionButton.setText(textId);
    }

}
