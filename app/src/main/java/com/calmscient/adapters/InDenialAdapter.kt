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
import com.calmscient.di.remote.ChangingResponseLessonThreeDataClass
import com.calmscient.di.remote.EmotionalSignsOFStressDataClass

class InDenialAdapter(
    private val context: Context,
    val items: MutableList<ChangingResponseLessonThreeDataClass>
) :
    RecyclerView.Adapter<InDenialAdapter.InDenialHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InDenialHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.changing_response_lesson_three_itemcard, parent, false)
        return InDenialHolder(itemView)
    }



    override fun onBindViewHolder(holder: InDenialHolder, position: Int) {
        val item = items[position]


        item.image?.let { holder.image.setImageResource(it) }

        if (item.descriptionOne.isNullOrBlank()) {
            holder.descriptionOne.visibility = View.GONE
        } else {
            holder.descriptionOne.text = item.descriptionOne
            holder.descriptionOne.visibility = View.VISIBLE
        }

        if (item.descriptionTwo.isNullOrBlank()) {
            holder.descriptionTwo.visibility = View.GONE
        } else {
            holder.descriptionTwo.text = item.descriptionTwo
            holder.descriptionTwo.visibility = View.VISIBLE
        }

        if (item.descriptionThree.isNullOrBlank()) {
            holder.descriptionThree.visibility = View.GONE
        } else {
            holder.descriptionThree.text = item.descriptionThree
            holder.descriptionThree.visibility = View.VISIBLE
        }


        if (item.headingOne.isNullOrBlank()) {
            holder.headingOne.visibility = View.GONE
        } else {
            holder.headingOne.text = item.headingOne
            holder.headingOne.visibility = View.VISIBLE
        }

        if (item.headingTwo.isNullOrBlank()) {
            holder.headingTwo.visibility = View.GONE
        } else {
            holder.headingTwo.text = item.headingTwo
            holder.headingTwo.visibility = View.VISIBLE
        }

        if (item.headingThree.isNullOrBlank()) {
            holder.headingThree.visibility = View.GONE
        } else {
            holder.headingThree.text = item.headingThree
            holder.headingThree.visibility = View.VISIBLE
        }

        if (item.headingFour.isNullOrBlank()) {
            holder.headingFour.visibility = View.GONE
        } else {
            holder.headingFour.text = item.headingFour
            holder.headingFour.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = items.size


    inner class InDenialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)

        val headingOne : TextView = itemView.findViewById(R.id.tv_heading_1)
        val headingTwo : TextView = itemView.findViewById(R.id.tv_heading_2)
        val headingThree : TextView = itemView.findViewById(R.id.tv_heading_3)
        val headingFour : TextView = itemView.findViewById(R.id.tv_heading_4)

        val descriptionOne : TextView = itemView.findViewById(R.id.description_1)
        val descriptionTwo: TextView = itemView.findViewById(R.id.description_2)
        val descriptionThree : TextView = itemView.findViewById(R.id.description_3)
    }


}
