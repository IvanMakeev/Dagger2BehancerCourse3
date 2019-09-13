package com.example.behancer.di.app

import com.example.behancer.di.fragment.FragmentComponent
import com.example.behancer.di.view.ViewModule
import com.example.behancer.ui.projects.ProjectsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(injector: ProjectsFragment)
    fun plusFragmentComponent(): FragmentComponent
}