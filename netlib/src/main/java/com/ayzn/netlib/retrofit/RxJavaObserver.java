package com.ayzn.netlib.retrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yangboy22 on 2018/1/19.
 */

public abstract class RxJavaObserver<T> implements Observer<T> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    protected final void dispose(){
        if(null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public abstract void onNext(T t);


    @Override
    public void onError(Throwable e) {
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }
}
