package com.example.behancer.ui.profile

import com.example.behancer.common.BaseView
import com.example.behancer.data.model.user.User


interface ProfileView : BaseView {

    fun showProfile()

    fun bind(user: User)

    fun getUsername() : String

}