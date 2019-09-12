package com.example.behancer.ui.profile

import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.ui.projects.ProjectsView
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter @Inject constructor() : BasePresenter() {

    private lateinit var view: ProfileView
    @Inject
    lateinit var storage: Storage
    @Inject
    lateinit var api: BehanceApi

    fun setView(view: ProfileView){
        this.view = view
    }

    fun getProfile() {
        compositeDisposable.add(
            api.getUserInfo(view.getUsername())
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response -> storage.insertUser(response) }
            .onErrorReturn { throwable ->
                if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java))
                    storage.getUser(view.getUsername())
                else
                    null
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showRefresh() }
            .doFinally { view.hideRefresh() }
            .subscribe(
                { response ->
                    view.showProfile()
                    view.bind(response.user)
                },
                {
                    view.showError()
                })
        )
    }
}