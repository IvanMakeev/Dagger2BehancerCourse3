package com.example.behancer.ui.projects

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.behancer.R
import com.example.behancer.data.model.project.Project
import com.example.behancer.utils.DateUtils
import com.squareup.picasso.Picasso

class ProjectsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {

        private const val FIRST_OWNER_INDEX = 0
    }

    private val mImage: ImageView
    private val mName: TextView
    private val mUsername: TextView
    private val mPublishedOn: TextView

    init {
        mImage = itemView.findViewById(R.id.image)
        mName = itemView.findViewById(R.id.tv_name)
        mUsername = itemView.findViewById(R.id.tv_username)
        mPublishedOn = itemView.findViewById(R.id.tv_published)
    }

    fun bind(item: Project, onItemClickListener: ProjectsAdapter.OnItemClickListener?) {
        Picasso.with(mImage.context).load(item.cover!!.photoUrl)
            .fit()
            .into(mImage)

        mName.text = item.name
        mUsername.text = item.owners!![FIRST_OWNER_INDEX].username
        mPublishedOn.text = DateUtils.format(item.publishedOn)

        if (onItemClickListener != null) {
            itemView.setOnClickListener { v ->
                onItemClickListener.onItemClick(
                    item.owners!![FIRST_OWNER_INDEX].username
                )
            }
        }
    }

}
