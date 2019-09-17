package com.example.behancer.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.behancer.AppDelegate
import com.example.behancer.R
import com.example.behancer.common.PresenterFragment
import com.example.behancer.common.RefreshOwner
import com.example.behancer.common.Refreshable
import com.example.behancer.data.model.user.User
import com.example.behancer.utils.DateUtils
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProfileFragment : PresenterFragment(), ProfileView, Refreshable {

    companion object {
        const val PROFILE_KEY = "PROFILE_KEY"
        fun newInstance(args: Bundle) = ProfileFragment().apply { arguments = args }
    }

    @Inject
    @InjectPresenter
    lateinit var _presenter: ProfilePresenter

    private lateinit var errorView: View
    private lateinit var profileView: View
    private lateinit var username: String
    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileCreatedOn: TextView
    private lateinit var profileLocation: TextView
    private var refreshOwner: RefreshOwner? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RefreshOwner) {
            refreshOwner = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        errorView = view.findViewById(R.id.errorView)
        profileView = view.findViewById(R.id.view_profile)
        profileImage = view.findViewById(R.id.iv_profile)
        profileName = view.findViewById(R.id.tv_display_name_details)
        profileCreatedOn = view.findViewById(R.id.tv_created_on_details)
        profileLocation = view.findViewById(R.id.tv_location_details)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        username = arguments?.getString(PROFILE_KEY)!!
        activity?.let {
            it.title = username
        }
        profileView.visibility = View.VISIBLE
        onRefreshData()
    }

    override fun onRefreshData() {
        _presenter.getProfile(username)
    }

    override fun bind(user: User) {
        Picasso.with(context)
            .load(user.image!!.photoUrl)
            .fit()
            .into(profileImage)
        profileName.text = user.displayName
        profileCreatedOn.text = DateUtils.format(user.createdOn)
        profileLocation.text = user.location
    }

    override fun onDetach() {
        super.onDetach()
        refreshOwner = null
    }

    override fun getPresenter(): ProfilePresenter {
        return _presenter
    }

    @ProvidePresenter
    fun providePresenter():ProfilePresenter{
        AppDelegate.getInjector().plusFragmentComponent().inject(this)
        return  _presenter
    }

    override fun showProfile() {
        errorView.visibility = View.GONE
        profileView.visibility = View.VISIBLE
    }

    override fun showRefresh() {
        refreshOwner!!.setRefreshState(true)
    }

    override fun hideRefresh() {
        refreshOwner!!.setRefreshState(false)
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
        profileView.visibility = View.GONE
    }
}