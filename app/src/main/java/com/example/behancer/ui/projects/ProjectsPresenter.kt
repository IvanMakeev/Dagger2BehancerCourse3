package com.example.behancer.ui.projects

import com.arellomobile.mvp.InjectViewState
import com.example.behancer.BuildConfig
import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ProjectsPresenter @Inject constructor(
    private val storage: Storage,
    private val api: BehanceApi
) : BasePresenter<ProjectsView>() {

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
            .doOnSubscribe { viewState.showRefresh() }
            .doFinally { viewState.hideRefresh() }
            .subscribe(
                { response ->
                    viewState.showProjects(response.projects)
                },
                {
                    it.printStackTrace()
                    viewState.showError()
                })
        )
    }

    fun openProfileFragment(username: String) {
        viewState.openProfileFragment(username)
    }
}