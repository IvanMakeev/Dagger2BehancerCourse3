package com.example.behancer.di

import com.example.behancer.ui.profile.ProfilePresenter
import com.example.behancer.ui.projects.ProjectsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(injector: ProjectsPresenter)
    fun inject(injector: ProfilePresenter)
    fun plusFragmentComponent(): FragmentComponent

}