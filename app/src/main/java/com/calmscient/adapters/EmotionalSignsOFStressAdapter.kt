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

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.di.remote.AnxietyAndSleepDataClass
import com.calmscient.di.remote.AnxietyBiasedDataClass
import com.calmscient.di.remote.EmotionalSignsOFStressDataClass

class EmotionalSignsOFStressAdapter(
    private val context: Context,
    val items: MutableList<EmotionalSignsOFStressDataClass>
) :
    RecyclerView.Adapter<EmotionalSignsOFStressAdapter.EmotionalSignsOFStressHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmotionalSignsOFStressHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.emotional_signs_of_stress_itemcard, parent, false)
        return EmotionalSignsOFStressHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmotionalSignsOFStressHolder, position: Int) {
        val item = items[position]


        item.image?.let { holder.image.setImageResource(it) }

        if (item.descriptionOne.isNullOrBlank()) {
            holder.text1.visibility = View.GONE
        } else {
            holder.text1.text = item.descriptionOne
            holder.text1.visibility = View.VISIBLE
        }

        if (item.descriptionTwo.isNullOrBlank()) {
            holder.text2.visibility = View.GONE
        } else {
            holder.text2.text = item.descriptionTwo
            holder.text2.visibility = View.VISIBLE
        }


        if (item.heading.isNullOrBlank()) {
            holder.textHeading2.visibility = View.GONE
        } else {
            holder.textHeading2.text = item.heading
            holder.textHeading2.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = items.size


    inner class EmotionalSignsOFStressHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
              val image: ImageView = itemView.findViewById(R.id.imageView)
        val text1: TextView = itemView.findViewById(R.id.tv_emotional_signs_of_stress_desc_1)
        val textHeading1: TextView = itemView.findViewById(R.id.tv_emotional_signs_sleep_heading)
        val text2: TextView = itemView.findViewById(R.id.tv_emotional_signs_of_stress_desc_2)
        val textHeading2: TextView = itemView.findViewById(R.id.tv_emotional_signs_sleep_heading)
    }


}
