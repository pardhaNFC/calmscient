/*
 *
 *      Copyright (c) 2023- NFC Solutions, - All Rights Reserved
 *      All source code contained herein remains the property of NFC Solutions Incorporated
 *      and protected by trade secret or copyright law of USA.
 *      Dissemination, De-compilation, Modification and Distribution are strictly prohibited unless
 *      there is a prior written permission or license agreement from NFC Solutions.
 *
 *      Author : @Pardha Saradhi
 */

package com.calmscient.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.PlayerActivity

data class VideoItem(
    val videoResourceId: Int,
    val thumbnailResourceId: Int
)

class VideoAdapter(private val videoItems: List<VideoItem>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoView: VideoView = itemView.findViewById(R.id.videoView)
        val videoThumbnailImageView: ImageView = itemView.findViewById(R.id.videoThumbnailImageView)

        init {
            /*videoView.setOnClickListener {
                if (videoView.isPlaying) {
                    videoView.pause()
                    videoThumbnailImageView.visibility = View.VISIBLE
                } else {
                    videoView.start()
                    videoThumbnailImageView.visibility = View.GONE
                }
            }*/
            videoView.setOnClickListener {
                val context = itemView.context
                val playerIntent = Intent(context, PlayerActivity::class.java)
                playerIntent.putExtra(
                    "videoResourceId",
                    videoItems[adapterPosition].videoResourceId
                )
                context.startActivity(playerIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoItems[position]

        val videoPath =
            "android.resource://${holder.videoView.context.packageName}/${videoItem.videoResourceId}"
        holder.videoView.setVideoURI(Uri.parse(videoPath))

        // Set the thumbnail for the video
        holder.videoThumbnailImageView.setImageResource(videoItem.thumbnailResourceId)

    }


    override fun getItemCount(): Int {
        return videoItems.size
    }
}