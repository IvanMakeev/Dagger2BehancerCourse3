package com.example.behancer.common

import com.arellomobile.mvp.MvpView


interface BaseView : MvpView {

    fun showRefresh()

    fun hideRefresh()

    fun showError()
}