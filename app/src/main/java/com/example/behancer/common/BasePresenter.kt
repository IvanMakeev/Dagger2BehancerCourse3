package com.example.behancer.common

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    protected val compositeDisposable = CompositeDisposable()

    fun disposeAll() {
        compositeDisposable.dispose()
    }
}


