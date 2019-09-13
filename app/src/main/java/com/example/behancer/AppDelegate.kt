package com.example.behancer

import android.app.Application
import com.example.behancer.common.PresenterFragment
import com.example.behancer.di.app.AppComponent
import com.example.behancer.di.app.AppModule
import com.example.behancer.di.app.DaggerAppComponent
import com.example.behancer.di.app.NetworkModule
import com.example.behancer.di.fragment.FragmentComponent
import com.example.behancer.di.view.ViewComponent
import com.example.behancer.di.view.ViewModule

class AppDelegate : Application() {

    companion object {
        private lateinit var INSTANCE: AppDelegate
        @JvmStatic
        fun getInjector(): AppDelegate = INSTANCE
    }

    private lateinit var appComponent: AppComponent
    private var fragmentComponent: FragmentComponent? = null
    private var viewComponent: ViewComponent? = null


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun getAppComponent() = appComponent

    fun plusFragmentComponent(): FragmentComponent {

        if (fragmentComponent == null) {
            fragmentComponent = appComponent.plusFragmentComponent()
        }
        return fragmentComponent as FragmentComponent
    }

    fun clearFragmentComponent() {
        fragmentComponent = null
    }

    fun plusViewComponent(presenterFragment: PresenterFragment<*>): ViewComponent {
        if (viewComponent == null) {
            viewComponent = plusFragmentComponent()
                .plusRefreshOwnerComponent(ViewModule(presenterFragment))
        }
        return viewComponent as ViewComponent
    }

    fun clearViewComponent() {
        viewComponent = null
    }

    fun getInjectView() = viewComponent
}
