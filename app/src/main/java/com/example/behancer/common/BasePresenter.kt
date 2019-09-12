package com.example.behancer.common

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    protected val mCompositeDisposable = CompositeDisposable()

    fun disposeAll() {
        mCompositeDisposable.dispose()
    }
}


