package com.example.behancer.di.view

import com.example.behancer.common.PresenterFragment
import com.example.behancer.common.RefreshOwner
import com.example.behancer.ui.profile.ProfileView
import com.example.behancer.ui.projects.ProjectsView
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val fragment: PresenterFragment<*>) {

    @Provides
    @View
    fun provideRefreshOwner() = fragment.activity as RefreshOwner

    @Provides
    @View
    fun provideProjectsView() = fragment as ProjectsView

    @Provides
    @View
    fun provideProfileView() = fragment as ProfileView
}