package com.example.behancer.ui.projects

import com.example.behancer.BuildConfig
import com.example.behancer.common.BasePresenter
import com.example.behancer.data.Storage
import com.example.behancer.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectsPresenter(private val view: ProjectsView, private val storage: Storage) : BasePresenter() {

    fun getProjects() {
        mCompositeDisposable.add(
            ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(storage::insertProjects)
                .onErrorReturn { throwable ->
                    if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.javaClass)) storage.getProjects()
                    else null
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { disposable -> view.showRefresh() }
                .doFinally(view::hideRefresh)
                .subscribe(
                    { response -> view.showProjects(response.projects) },
                    { throwable -> view.showError() })
        )
    }

    fun openProfileFragment(username: String) {
        view.openProfileFragment(username)
    }
}