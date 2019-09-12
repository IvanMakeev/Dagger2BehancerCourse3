package com.example.behancer.ui.projects

import com.example.behancer.common.BaseView
import com.example.behancer.data.model.project.Project

interface ProjectsView : BaseView {

    fun showProjects( projects: List<Project>)

    fun openProfileFragment( username: String)
}