package com.example.behancer.ui.projects

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.behancer.common.SingleFragmentActivity

class ProjectsActivity : SingleFragmentActivity() {

    override fun getFragment(): Fragment {
        return ProjectsFragment.newInstance()
    }
}
