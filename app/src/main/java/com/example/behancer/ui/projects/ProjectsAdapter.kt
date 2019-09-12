package com.example.behancer.ui.projects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.behancer.R
import com.example.behancer.data.model.project.Project
import java.util.ArrayList

class ProjectsAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProjectsHolder>() {

    private val projects = ArrayList<Project>()

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.li_projects, parent, false)
        return ProjectsHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsHolder, position: Int) {
        val project = projects[position]
        holder.bind(project, onItemClickListener)
    }

    fun addData(data: List<Project>, isRefreshed: Boolean) {
        if (isRefreshed) {
            projects.clear()
        }

        projects.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {

        fun onItemClick(username: String)
    }
}