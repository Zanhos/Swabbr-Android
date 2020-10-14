package com.laixer.swabbr.presentation.vlogs.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.laixer.presentation.inflate
import com.laixer.swabbr.R
import com.laixer.swabbr.presentation.loadAvatar
import com.laixer.swabbr.presentation.model.UserVlogItem
import kotlinx.android.synthetic.main.include_user_info.view.*
import kotlinx.android.synthetic.main.item_list_vlog.view.*
import java.lang.IllegalStateException
import java.time.ZonedDateTime

class VlogListAdapter constructor(
    private val itemClick: (UserVlogItem) -> Unit,
    private val profileClick: (UserVlogItem) -> Unit
) : ListAdapter<UserVlogItem, VlogListAdapter.ViewHolder>(VlogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_vlog)) {

        fun bind(item: UserVlogItem) = with(itemView) {
            if (item.vlog.data.dateStarted.isBefore(ZonedDateTime.now().minusMinutes(3))) {
                processing_cover.visibility = View.GONE

                val url = item.vlog.thumbnailUri.toString() ?: ""
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.thumbnail_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.1f)
                    .into(thumbnail)

                user_avatar.loadAvatar(item.user.profileImage, item.user.id)
                user_nickname.text = context.getString(R.string.nickname, item.user.nickname)
                item.user.firstName?.let {
                    user_username.apply {
                        text = context.getString(R.string.full_name, it, item.user.lastName)
                        visibility = View.VISIBLE
                    }
                }

                vlogPostDate.text =
                    context.getString(
                        R.string.date, item.vlog.data.dateStarted.dayOfMonth, item.vlog.data.dateStarted.monthValue, item.vlog.data.dateStarted.year
                    )

                vlogDuration.text =
                    context.getString(R.string.duration, (Math.random() * 10).toInt(), (Math.random() * 60).toInt())
                reaction_count.text = context.getString(R.string.reaction_count, (Math.random() * 100).toInt())

                view_count.text = context.getString(R.string.view_count, item.vlog.data.views)

                like_count.text = context.getString(R.string.like_count, item.vlog.vlogLikeSummary.totalLikes)

                user_avatar.setOnClickListener { profileClick.invoke(item) }
                user_nickname.text = context.getString(R.string.nickname, item.user.nickname)

                item.user.firstName?.let {
                    itemView.user_username.apply {
                        text = context.getString(R.string.full_name, it, item.user.lastName)
                        visibility = View.VISIBLE
                    }
                }

                setOnClickListener { itemClick.invoke(item) }
            }
        }
    }

    companion object {

        private const val THUMBNAIL_SIZE = 1f
    }
}

private class VlogDiffCallback : DiffUtil.ItemCallback<UserVlogItem>() {

    override fun areItemsTheSame(oldItem: UserVlogItem, newItem: UserVlogItem): Boolean =
        oldItem.vlog.data.id == newItem.vlog.data.id

    override fun areContentsTheSame(oldItem: UserVlogItem, newItem: UserVlogItem): Boolean = oldItem.vlog.data.id == newItem.vlog.data.id
}
