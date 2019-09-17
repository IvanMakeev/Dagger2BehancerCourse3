package com.example.behancer.ui.projects

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.behancer.common.BaseView
import com.example.behancer.data.model.project.Project

interface ProjectsView : BaseView {

    fun showProjects(projects: List<Project>)

    @StateStrategyType(value = SkipStrategy::class)
    fun openProfileFragment(username: String)
}