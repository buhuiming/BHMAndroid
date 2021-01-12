package com.bhm.sdk.bhmlibrary.result;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by bhm on 2018/9/19.
 */

public class ResultFragment extends Fragment {
    private Map<Integer, PublishSubject<ResultData>> mSubjects = new HashMap<>();
    private Map<Integer, ActivityResult.Callback> mCallbacks = new HashMap<>();

    public ResultFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<ResultData> startForResult(final Intent intent) {
        final PublishSubject<ResultData> subject = PublishSubject.create();
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mSubjects.put(subject.hashCode(), subject);
                startActivityForResult(intent, subject.hashCode());
            }
        });
    }

    public void startForResult(Intent intent, ActivityResult.Callback callback) {

        mCallbacks.put(callback.hashCode(), callback);
        startActivityForResult(intent, callback.hashCode());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //rxjava方式的处理
        PublishSubject<ResultData> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            subject.onNext(new ResultData(resultCode, data));
            subject.onComplete();
        }

        //callback方式的处理
        ActivityResult.Callback callback = mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onActivityResult( resultCode, data);
        }
    }

}
