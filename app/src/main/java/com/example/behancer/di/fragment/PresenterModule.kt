package com.example.behancer.di.fragment

import com.example.behancer.data.Storage
import com.example.behancer.data.api.BehanceApi
import com.example.behancer.di.fragment.PerFragment
import com.example.behancer.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    @PerFragment
    fun provideProfilePresenter(storage: Storage, api: BehanceApi) = ProfilePresenter(storage, api)
}