package com.festivality.conferenceapp.helper.ui;


/**
 * Created by ankumar on 11/14/2017.
 * Util class for navigating common page in application
 */

public class NavigatorHelper {

    private static final String TAG_LOGIN = "TAG_LOGIN";
    private static final String TAG_PROFILE = "TAG_PROFILE";
    private static final String TAG_ALL_REPO = "TAG_ALL_REPO";

    private final Navigator mNavigator;

    public NavigatorHelper(Navigator navigator) {
        this.mNavigator = navigator;
    }

    /*public void navigateLoginActivity(boolean clearAllPrevious) {
        mNavigator.startActivity(LoginActivity.class, intent -> {
            if (clearAllPrevious) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
        if (clearAllPrevious) {
            mNavigator.finishActivity();
        }
    }

    public void navigateHomeActivity(boolean clearAllPrevious) {
        mNavigator.startActivity(HomeActivity.class, intent -> {
            if (clearAllPrevious) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
        if (clearAllPrevious) {
            mNavigator.finishActivity();
        }
    }*/

   /* public void navigateUserProfile(@Nullable CurrentUser user) {
        mNavigator.startActivity(ProfileActivity.class, intent -> {
            intent.putExtra(BundleConstant.EXTRA, user);
        });
    }

    public void navigateRepoDetail(@NonNull Repo repo) {
        mNavigator.startActivity(RepoDetailActivity.class, intent -> {
            intent.putExtra(BundleConstant.EXTRA, repo);
        });
    }

    public void navigateIssuePagerActivity(Issue issue) {
//        PullsIssuesParser parser = PullsIssuesParser.getForIssue(issue.getHtmlUrl());
        mNavigator.startActivity(IssueDetailActivity.class, intent -> {
            intent.putExtra(BundleConstant.ITEM, issue);
        });
    }

    public void navigateIssuePagerActivity(PullsIssuesParser parser) {

    }

    public void navigateCodeViewerActivity(String url, String htmlUrl) {
        mNavigator.startActivity(CodeViewerActivity.class, intent -> {
            boolean isEnterprise = LinkParserHelper.isEnterprise(htmlUrl);
            intent.putExtras(Bundler.start()
                    .put(BundleConstant.EXTRA_TWO, htmlUrl)
                    .put(BundleConstant.EXTRA, url)
                    .put(BundleConstant.IS_ENTERPRISE, isEnterprise)
                    .end());
        });
    }*/
//
//    public void navigateUserProfileActivity(@Nullable CurrentUser user, View... transitionViews) {
//        mNavigator.startActivityWithTransition(ProfileActivity.class, intent -> {
//            intent.putExtra(Constants.EXTRA_DATA, Parcels.wrap(user));
//        }, false, false, transitionViews);
//    }
//
//    public void navigateLoginFragment(@IdRes int containerId) {
//        LoginFragment fragment = mNavigator.findFragmentByTag(TAG_LOGIN);
//        if (fragment == null) {
//            fragment = new LoginFragment();
//        }
//        mNavigator.replaceFragment(containerId, fragment, TAG_LOGIN, null);
//    }
//
//    public void navigateAllRepositoriesFragment(@IdRes int containerId) {
//        RepositoriesFragment fragment = mNavigator.findFragmentByTag(TAG_ALL_REPO);
//        if (fragment == null) {
//            fragment = new RepositoriesFragment();
//        }
//        mNavigator.replaceFragment(containerId, fragment, TAG_ALL_REPO, null);
//    }
//
//    public void navigateMyRepositoriesFragment(@IdRes int containerId) {
//        mNavigator.replaceFragment(containerId, UserRepositoryFragment.createInstance(null));
//    }
//
//    public void navigateRepositoryDetail(@NonNull Long repoId, View... views) {
//        mNavigator.startActivity(RepositoryDetailActivity.class, intent -> {
//            intent.putExtra(Constants.EXTRA_DATA, repoId);
//        });
//    }
}