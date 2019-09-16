package com.example.behancer.ui.projects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.behancer.AppDelegate
import com.example.behancer.R
import com.example.behancer.common.PresenterFragment
import com.example.behancer.common.RefreshOwner
import com.example.behancer.common.Refreshable
import com.example.behancer.data.model.project.Project
import com.example.behancer.ui.profile.ProfileActivity
import com.example.behancer.ui.profile.ProfileFragment
import javax.inject.Inject

class ProjectsFragment : PresenterFragment<ProjectsPresenter>(), Refreshable, ProjectsView,
    ProjectsAdapter.OnItemClickListener {

    companion object {
        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }

    @Inject
    lateinit var _presenter: ProjectsPresenter


    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: View
    private lateinit var refreshOwner: RefreshOwner
    private lateinit var projectsAdapter: ProjectsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RefreshOwner) {
            refreshOwner = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.getInjector().getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_projects, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler)
        errorView = view.findViewById(R.id.errorView)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.setTitle(R.string.projects)

        _presenter.setView(this)
        projectsAdapter = ProjectsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = projectsAdapter

        onRefreshData()
    }

    override fun onStart() {
        super.onStart()
        AppDelegate.getInjector().clearFragmentComponent()
    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun getPresenter(): ProjectsPresenter {
        return _presenter
    }

    override fun onRefreshData() {
        _presenter.getProjects()
    }

    override fun showRefresh() {
        refreshOwner.setRefreshState(true)
    }

    override fun hideRefresh() {
        refreshOwner.setRefreshState(false)
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun openProfileFragment(username: String) {
        val intent = Intent(activity, ProfileActivity::class.java)
        val args = Bundle()
        args.putString(ProfileFragment.PROFILE_KEY, username)
        intent.putExtra(ProfileActivity.USERNAME_KEY, args)
        startActivity(intent)

    }

    override fun showProjects(projects: List<Project>) {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        projectsAdapter.addData(projects, true)
    }

    override fun onItemClick(username: String) {
        _presenter.openProfileFragment(username)
    }
}