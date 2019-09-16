package com.example.behancer.ui.profile

import android.util.Log
import com.example.behancer.AppDelegate
import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter(private val storage: Storage, private val api: BehanceApi) : BasePresenter() {

    init {
        Log.d("TAG", "presenter is created")
    }

    @Inject
    lateinit var view: ProfileView

    fun getProfile() {
        injectViewInstance()
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

    private fun injectViewInstance() = AppDelegate.getInjector().getInjectorView()?.inject(this)
}