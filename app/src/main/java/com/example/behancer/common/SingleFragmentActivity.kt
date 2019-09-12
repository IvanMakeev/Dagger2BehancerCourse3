package com.example.behancer.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.behancer.R

abstract class SingleFragmentActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    RefreshOwner {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    protected abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_swipe_container)
        swipeRefreshLayout = findViewById(R.id.refresher)
        swipeRefreshLayout.setOnRefreshListener(this)

        if (savedInstanceState == null) {
            changeFragment(getFragment())
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val addToBackStack = supportFragmentManager.findFragmentById(R.id.fragmentContainer) != null

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }

        transaction.commit()
    }

    override fun onRefresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment is Refreshable) {
            fragment.onRefreshData()
        } else {
            setRefreshState(false)
        }
    }


    override fun setRefreshState(refreshing: Boolean) {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = refreshing }
    }
}