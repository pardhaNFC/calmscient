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
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.AnxietyPlayerActivity
import com.calmscient.activities.AnxietyQuestionsActivity
import com.calmscient.activities.AnxietyQuizActivity
import com.calmscient.activities.AudioPlayerActivity
import com.calmscient.activities.FastPaceActivity
import com.calmscient.activities.MakeAPlanActivity
import com.calmscient.activities.PlayerActivity
import com.calmscient.activities.RecognizeActivity
import com.calmscient.di.remote.ItemType
import com.calmscient.di.remote.CardItemDataClass

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
        private val introCardView: CardView = itemView.findViewById(R.id.anxietyCard)

        fun bind(cardItem: CardItemDataClass) {
            contentIcon.setImageResource(cardItem.contentIcons[0])
            descriptionTextView.text = cardItem.description

            if (cardItem.isCompleted) {
                tickImageView.visibility = View.VISIBLE
            } else {
                tickImageView.visibility = View.GONE
            }
            /*introCardView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, AnxietyQuestionsActivity::class.java)
                context.startActivity(intent)
            }*/
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
                            if (cardItem.description == context.getString(R.string.meet_nora_austin)) {
                                val intent = Intent(context, AudioPlayerActivity::class.java)
                                intent.putExtra("audioResourceId", cardItem.audioResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("description", cardItem.description)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            } else if (cardItem.description == context.getString(R.string.moral_deficiency)) {
                                val intent = Intent(context, AudioPlayerActivity::class.java)
                                intent.putExtra("audioResourceId", cardItem.audioResourceId)
                                intent.putExtra("description", cardItem.description)
                                /*intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)*/
                                context.startActivity(intent)
                            } else if (cardItem.description == context.getString(R.string.anxiety_worry)) {
                                val intent = Intent(context, AudioPlayerActivity::class.java)
                                intent.putExtra("audioResourceId", cardItem.audioResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("description", cardItem.description)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            }
                            else if (cardItem.description == context.getString(R.string.anxious_mind)) {
                                val intent = Intent(context, AudioPlayerActivity::class.java)
                                intent.putExtra("audioResourceId", cardItem.audioResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("description", cardItem.description)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            }
                            else {
                                Toast.makeText(context, "Soon", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "No Audio ", Toast.LENGTH_SHORT).show()
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
                            if (cardItem.heading == context.getString(R.string.the_neuropsychology)) {
                                val intent = Intent(context, PlayerActivity::class.java)
                                intent.putExtra("mediaResourceId", cardItem.videoResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            } else if (cardItem.heading == context.getString(R.string.page_2_1)) {
                                val intent = Intent(context, PlayerActivity::class.java)
                                intent.putExtra("mediaResourceId", cardItem.videoResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            }
                            else if (cardItem.heading == context.getString(R.string.make_plan_card6_text2)) {
                                val intent = Intent(context, PlayerActivity::class.java)
                                intent.putExtra("mediaResourceId", cardItem.videoResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            }else if (cardItem.heading == context.getString(R.string.anxiety_exercise)) {
                                val intent = Intent(context, PlayerActivity::class.java)
                                intent.putExtra("mediaResourceId", cardItem.videoResourceId)
                                intent.putExtra("heading", cardItem.heading)
                                intent.putExtra("summary", cardItem.summary)
                                intent.putExtra("dialogText", cardItem.dialogText)
                                context.startActivity(intent)
                            }
                        } else {
                            Toast.makeText(context, "No Video Available", Toast.LENGTH_SHORT).show()
                        }
                    }

                    cardItem.availableContentTypes.contains(ItemType.LESSON) -> {
                        //Toast.makeText(context, "No Lesson Available", Toast.LENGTH_SHORT).show()
                        /*val intent = Intent(context, AnxietyQuestionsActivity::class.java)
                        intent.putExtra("description", cardItem.description)
                        context.startActivity(intent)*/
                        if (cardItem.description == context.getString(R.string.page_2_1)) {
                            val intent = Intent(context, AnxietyQuestionsActivity::class.java)
                            intent.putExtra("description", cardItem.description)
                            context.startActivity(intent)
                        } else if (cardItem.description == context.getString(R.string.title_toolbar_pace)) {
                            val intent = Intent(context, FastPaceActivity::class.java)
                            intent.putExtra("description", cardItem.description)
                            context.startActivity(intent)
                        } else if (cardItem.description == context.getString(R.string.let_make_plan)) {
                            val intent = Intent(context, MakeAPlanActivity::class.java)
                            intent.putExtra("description", cardItem.description)
                            context.startActivity(intent)
                        } else if (cardItem.description == context.getString(R.string.title_toolbar_recognize)) {
                            val intent = Intent(context, RecognizeActivity::class.java)
                            intent.putExtra("description", cardItem.description)
                            context.startActivity(intent)
                        }
                    }

                    cardItem.availableContentTypes.contains(ItemType.QUIZ) -> {
                        // Toast.makeText(context, "No Quiz Available", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, AnxietyQuizActivity::class.java)
                        context.startActivity(intent)
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

    override fun areContentsTheSame(
        oldItem: CardItemDataClass,
        newItem: CardItemDataClass
    ): Boolean {
        return oldItem == newItem
    }
}