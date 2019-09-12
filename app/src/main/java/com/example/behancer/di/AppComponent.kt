package com.example.behancer.di

import com.example.behancer.ui.profile.ProfileFragment
import com.example.behancer.ui.projects.ProjectsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(injector: ProjectsFragment)
    fun inject(injector: ProfileFragment)
}