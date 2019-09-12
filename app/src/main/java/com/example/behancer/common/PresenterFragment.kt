package com.example.behancer.common

import androidx.fragment.app.Fragment


abstract class PresenterFragment<P : BasePresenter> : Fragment() {

    protected abstract fun getPresenter(): P

    override fun onDetach() {
            getPresenter().disposeAll()
        super.onDetach()
    }
}