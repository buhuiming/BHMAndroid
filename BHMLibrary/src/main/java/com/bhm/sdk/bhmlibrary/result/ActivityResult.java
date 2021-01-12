package com.bhm.sdk.bhmlibrary.result;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import io.reactivex.Observable;

/**
 * Created by bhm on 2018/9/19.
 */

public class ActivityResult {
    
    private static final String TAG = "ActivityResult";
    private ResultFragment resultFragment;

    public ActivityResult(Activity activity) {
        resultFragment = getResultFragment(activity);
    }

    public ActivityResult(Fragment fragment){
        this(fragment.getActivity());
    }

    private ResultFragment getResultFragment(Activity activity) {
        ResultFragment resultFragment = findResultFragment(activity);
        if (resultFragment == null) {
            resultFragment = new ResultFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(resultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return resultFragment;
    }

    private ResultFragment findResultFragment(Activity activity) {
        return (ResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public Observable<ResultData> startForResult(Intent intent) {
        return resultFragment.startForResult(intent);
    }

    public Observable<ResultData> startForResult(Class<?> clazz) {
        Intent intent = new Intent(resultFragment.getActivity(), clazz);
        return startForResult(intent);
    }

    public void startForResult(Intent intent, Callback callback) {
        resultFragment.startForResult(intent, callback);
    }

    public void startForResult(Class<?> clazz, Callback callback) {
        Intent intent = new Intent(resultFragment.getActivity(), clazz);
        startForResult(intent, callback);
    }

    public interface Callback {
        void onActivityResult(int resultCode, Intent data);
    }
}
