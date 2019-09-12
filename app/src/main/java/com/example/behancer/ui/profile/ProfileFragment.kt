package com.example.behancer.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.behancer.R
import com.example.behancer.common.RefreshOwner
import com.example.behancer.common.Refreshable
import com.example.behancer.data.Storage
import com.example.behancer.data.model.user.User
import com.example.behancer.utils.ApiUtils
import com.example.behancer.utils.DateUtils
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileFragment : Fragment(), Refreshable {

    companion object {

        const val PROFILE_KEY = "PROFILE_KEY"

        fun newInstance(args: Bundle): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private var refreshOwner: RefreshOwner? = null
    private lateinit var errorView: View
    private lateinit var profileView: View
    private lateinit var username: String
    private var storage: Storage? = null
    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileCreatedOn: TextView
    private lateinit var profileLocation: TextView
    private lateinit var disposable: Disposable

    override fun onAttach(context: Context) {
        super.onAttach(context)
        storage = if (context is Storage.StorageOwner) context.obtainStorage() else null
        refreshOwner = if (context is RefreshOwner) context else null
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

        arguments?.let {
            username = it.getString(PROFILE_KEY).toString()
        }

        activity?.let {
            it.title = username
        }

        profileView.visibility = View.VISIBLE

        onRefreshData()
    }

    override fun onRefreshData() {
        getProfile()
    }

    private fun getProfile() {
        disposable = ApiUtils.getApiService().getUserInfo(username)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response -> storage!!.insertUser(response) }
            .onErrorReturn { throwable ->
                if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.javaClass))
                    storage!!.getUser(username)
                else
                    null
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable -> refreshOwner!!.setRefreshState(true) }
            .doFinally { refreshOwner!!.setRefreshState(false) }
            .subscribe(
                { response ->
                    errorView.visibility = View.GONE
                    profileView.visibility = View.VISIBLE
                    bind(response.user)
                },
                { throwable ->
                    errorView.visibility = View.VISIBLE
                    profileView.visibility = View.GONE
                })
    }

    private fun bind(user: User) {
        Picasso.with(context)
            .load(user.image!!.photoUrl)
            .fit()
            .into(profileImage)
        profileName.text = user.displayName
        profileCreatedOn.text = DateUtils.format(user.createdOn)
        profileLocation.text = user.location
    }

    override fun onDetach() {
        storage = null
        refreshOwner = null
        disposable.dispose()
        super.onDetach()
    }
}