package com.example.behancer.di

import com.example.behancer.ui.profile.ProfileFragment
import com.example.behancer.ui.projects.ProjectsFragment
import dagger.Subcomponent


@PerFragment
@Subcomponent
interface FragmentComponent {
    fun inject(injector: ProfileFragment)
    fun inject(injector: ProjectsFragment)
}