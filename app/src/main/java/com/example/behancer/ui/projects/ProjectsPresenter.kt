package com.example.behancer.ui.projects

import com.example.behancer.BuildConfig
import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectsPresenter @Inject constructor() : BasePresenter() {

    private lateinit var view: ProjectsView
    @Inject
    lateinit var storage: Storage
    @Inject
    lateinit var api: BehanceApi

    fun setView(view: ProjectsView) {
        this.view = view
    }

    fun getProjects() {
        compositeDisposable.add(api.getProjects(BuildConfig.API_QUERY)
            .doOnSuccess { response -> storage.insertProjects(response) }
            .onErrorReturn { throwable ->
                if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java))
                    storage.getProjects()
                else
                    null
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showRefresh() }
            .doFinally { view.hideRefresh() }
            .subscribe(
                { response ->
                    view.showProjects(response.projects)
                },
                {
                    it.printStackTrace()
                    view.showError()
                })
        )
    }

    fun openProfileFragment(username: String) {
        view.openProfileFragment(username)
    }
}