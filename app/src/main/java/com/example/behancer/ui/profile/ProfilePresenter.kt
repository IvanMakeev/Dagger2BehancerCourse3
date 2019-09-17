package com.example.behancer.ui.profile

import com.arellomobile.mvp.InjectViewState
import com.example.behancer.AppDelegate
import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {

    init {
        AppDelegate.getInjector().getAppComponent().inject(this)
    }

    @Inject
    lateinit var storage: Storage
    @Inject
    lateinit var api: BehanceApi

    fun getProfile(username: String) {
        compositeDisposable.add(
            api.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess { response -> storage.insertUser(response) }
                .onErrorReturn { throwable ->
                    if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java))
                        storage.getUser(username)
                    else
                        null
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showRefresh() }
                .doFinally { viewState.hideRefresh() }
                .subscribe(
                    { response ->
                        viewState.showProfile()
                        viewState.bind(response.user)
                    },
                    {
                        viewState.showError()
                    })
        )
    }
}