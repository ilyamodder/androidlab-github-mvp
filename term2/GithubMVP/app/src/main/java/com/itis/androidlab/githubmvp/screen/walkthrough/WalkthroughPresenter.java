package com.itis.androidlab.githubmvp.screen.walkthrough;

import com.itis.androidlab.githubmvp.R;
import com.itis.androidlab.githubmvp.content.Benefit;
import com.itis.androidlab.githubmvp.utils.PreferenceUtils;

import java.util.ArrayList;

/**
 * @author ilya
 */

public class WalkthroughPresenter {

    private static final int PAGES_COUNT = 3;

    private WalkthroughView mView;
    private int mCurrentItem;

    public WalkthroughPresenter(WalkthroughView view) {
        mView = view;
        mCurrentItem = 0;
    }

    public void init() {
        mView.setButtonText(R.string.next_uppercase);
        if (PreferenceUtils.isWalkthroughPassed()) {
            mView.showLoginScreen();
        } else {
            mView.setBenefits(new ArrayList<Benefit>() {
                {
                    add(Benefit.WORK_TOGETHER);
                    add(Benefit.CODE_HISTORY);
                    add(Benefit.PUBLISH_SOURCE);
                }
            });
        }
    }

    public void onButtonClick() {
        if (isLastBenefit()) {
            PreferenceUtils.saveWalkthroughPassed();
            mView.showLoginScreen();
        } else {
            mCurrentItem++;
            mView.showBenefit(mCurrentItem);
        }
    }

    public void onPageChanged(int selectedPage, boolean fromUser) {
        if (fromUser) {
            mCurrentItem = selectedPage;
            mView.showBenefit(mCurrentItem);
        }

        mView.setButtonText(isLastBenefit() ? R.string.finish_uppercase : R.string.next_uppercase);
    }

    private boolean isLastBenefit() {
        return mCurrentItem == PAGES_COUNT - 1;
    }
}
