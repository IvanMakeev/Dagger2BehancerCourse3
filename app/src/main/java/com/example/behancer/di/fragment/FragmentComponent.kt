package com.example.behancer.di.fragment

import com.example.behancer.di.view.ViewComponent
import com.example.behancer.di.view.ViewModule
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PresenterModule::class])
interface FragmentComponent {
    fun plusRefreshOwnerComponent(viewModule: ViewModule): ViewComponent
}