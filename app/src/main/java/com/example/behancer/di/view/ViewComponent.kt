package com.example.behancer.di.view

import com.example.behancer.ui.profile.ProfileFragment
import com.example.behancer.ui.profile.ProfilePresenter
import com.example.behancer.ui.projects.ProjectsFragment
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = [ViewModule::class])
interface ViewComponent {
    fun inject(injector: ProjectsFragment)
    fun inject(injector: ProfileFragment)
    fun inject(injector: ProfilePresenter)
}