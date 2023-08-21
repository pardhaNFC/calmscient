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
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.AnxietyPlayerActivity
import com.calmscient.data.remote.ItemType
import com.calmscient.data.remote.CardItemDataClass

class AnxietyIntroductionAdapter(private val diffCallback: DiffUtil.ItemCallback<CardItemDataClass>) :
    ListAdapter<CardItemDataClass, AnxietyIntroductionAdapter.ViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.anxiety_item_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardItem = getItem(position)
        holder.bind(cardItem)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contentIcon: ImageView = itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val tickImageView: ImageView = itemView.findViewById(R.id.tickImageView)

        fun bind(cardItem: CardItemDataClass) {
            contentIcon.setImageResource(cardItem.contentIcons[0])
            descriptionTextView.text = cardItem.description

            if (cardItem.isCompleted) {
                tickImageView.visibility = View.VISIBLE
            } else {
                tickImageView.visibility = View.GONE
            }

            itemView.setOnClickListener {
                val context = itemView.context
                when {
                    /*cardItem.availableContentTypes.contains(ItemType.AUDIO) -> {
                        if (cardItem.audioResourceId != null) {
                            val intent = Intent(context, AnxietyPlayerActivity::class.java)
                            intent.putExtra("contentUri", Uri.parse("android.resource://${context.packageName}/${cardItem.audioResourceId}"))
                            context.startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(context, "No Audio Available", Toast.LENGTH_SHORT).show()
                        }
                    }*/
                    cardItem.availableContentTypes.contains(ItemType.AUDIO) -> {
                        if (cardItem.audioResourceId != null) {
                            val intent = Intent(context, AnxietyPlayerActivity::class.java)
                            intent.putExtra("mediaResourceId", cardItem.audioResourceId)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No Audio Available", Toast.LENGTH_SHORT).show()
                        }
                    }
                    /*cardItem.availableContentTypes.contains(ItemType.VIDEO) -> {
                        if (cardItem.videoResourceId != null) {
                            val intent = Intent(context, AnxietyPlayerActivity::class.java)
                            intent.putExtra("contentUri", Uri.parse("android.resource://${context.packageName}/${cardItem.videoResourceId}"))
                            context.startActivity(intent)

                        }
                        else
                        {
                            Toast.makeText(context, "No Video Available", Toast.LENGTH_SHORT).show()
                        }
                    }*/
                    cardItem.availableContentTypes.contains(ItemType.VIDEO) -> {
                        if (cardItem.videoResourceId != null) {
                            val intent = Intent(context, AnxietyPlayerActivity::class.java)
                            intent.putExtra("mediaResourceId", cardItem.videoResourceId)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No Video Available", Toast.LENGTH_SHORT).show()
                        }
                    }
                    cardItem.availableContentTypes.contains(ItemType.LESSON) -> {
                        Toast.makeText(context, "No Lesson Available", Toast.LENGTH_SHORT).show()
                        /*val intent = Intent(context, LessonActivity::class.java)
                        context.startActivity(intent)*/
                    }
                    cardItem.availableContentTypes.contains(ItemType.QUIZ) -> {
                        Toast.makeText(context, "No Quiz Available", Toast.LENGTH_SHORT).show()
                        /*val intent = Intent(context, QuizActivity::class.java)
                        context.startActivity(intent)*/
                    }
                    else -> {
                        // Handle other content types here
                    }
                }
            }
        }
    }
}

class CardItemDiffCallback : DiffUtil.ItemCallback<CardItemDataClass>() {
    override fun areItemsTheSame(oldItem: CardItemDataClass, newItem: CardItemDataClass): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CardItemDataClass, newItem: CardItemDataClass): Boolean {
        return oldItem == newItem
    }
}