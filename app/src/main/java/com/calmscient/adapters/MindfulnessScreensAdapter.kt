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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.data.remote.MindfulnessExercisesTextDataClass
class MindfulnessScreensAdapter (private val items: List<MindfulnessExercisesTextDataClass>) :
    RecyclerView.Adapter<MindfulnessScreensAdapter.MindfullnessQuestionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MindfullnessQuestionsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercises_items, parent, false)
        return MindfullnessQuestionsViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MindfullnessQuestionsViewHolder, position: Int) {
        val item = items[position]

        holder.text1.text = item.text1
        holder.text2.text = item.text2
        item.exercisesBellIcon?.let { holder.exercisesBellIcon.setImageResource(it) }
        item.exercisesHeartImage?.let { holder.exercisesHeartImage.setImageResource(it) }
        item.imageAnxiety?.let { holder.imageAnxiety.setImageResource(it) }

    }

    inner class MindfullnessQuestionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text1)
        val text2: TextView = itemView.findViewById(R.id.text2)
        val exercisesBellIcon: ImageView = itemView.findViewById(R.id.exercisesBellIcon)
        val exercisesHeartImage: ImageView = itemView.findViewById(R.id.exercisesHeartImage)
        val imageAnxiety: ImageView = itemView.findViewById(R.id.imageAnxiety)

    }
}
