package com.itis.androidlab.githubmvp.screen.commits;

import com.itis.androidlab.githubmvp.content.Commit;
import com.itis.androidlab.githubmvp.screen.general.LoadingView;

import java.util.List;

/**
 * @author ilya
 */

public interface CommitsView extends LoadingView {
    void showCommits(List<Commit> commits);

    void showError();
}
