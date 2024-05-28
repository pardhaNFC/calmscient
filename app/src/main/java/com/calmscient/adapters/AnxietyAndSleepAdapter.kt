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

class AnxietyAndSleepAdapter(private val context: Context, val items: MutableList<AnxietyAndSleepDataClass>) :
    RecyclerView.Adapter<AnxietyAndSleepAdapter.AnxietyAndSleepViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnxietyAndSleepViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.anxity_and_sleep_item_card, parent, false)
        return AnxietyAndSleepViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnxietyAndSleepViewHolder, position: Int) {
        val item = items[position]

        item.bulbImage?.let { holder.bulb.setImageResource(it) }

        item.image?.let { holder.image.setImageResource(it) }

        // Check and set text1
        if (item.textDesc1.isNullOrBlank()) {
            holder.text1.visibility = View.GONE
        } else {
            holder.text1.text = item.textDesc1
            holder.text1.visibility = View.VISIBLE
        }
        // Check and set text2
        if (item.textDesc2.isNullOrBlank()) {
            holder.text2.visibility = View.GONE
        } else {
            holder.text2.text = item.textDesc2
            holder.text2.visibility = View.VISIBLE
        }

        // Set anxietybulb and imageanxiety unconditionally
        // item.anxietybulb?.let { holder.anxietybulb.setImageResource(it) }


        if (item.heading.isNullOrBlank()) {
            holder.textHeading1.visibility = View.GONE
        } else {
            holder.textHeading1.text = item.heading
            holder.textHeading1.visibility = View.VISIBLE
        }

        // Check and set text3
        if (item.finalText.isNullOrBlank()) {
            holder.textHeading2.visibility = View.GONE
        } else {
            holder.textHeading2.text = item.finalText
            holder.textHeading2.visibility = View.VISIBLE
        }

        holder.bulb.setOnClickListener { 
            showInformationDialog()
        }

    }

    private fun showInformationDialog() {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.recognize_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)


        titleTextView.text = context.getString(R.string.did_you_know)
        infoTextView.text = context.getString(R.string.anxiety_and_sleep_card_2_dialog_text)
        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun getItemCount(): Int = items.size



    inner class AnxietyAndSleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bulb :ImageView = itemView.findViewById(R.id.bulb_icon)
        val image :ImageView = itemView.findViewById(R.id.imageView)
        val text1: TextView = itemView.findViewById(R.id.tv_anxiety_and_sleep_desc_1)
        val textHeading1: TextView = itemView.findViewById(R.id.tv_anxiety_and_sleep_heading)
        val text2: TextView = itemView.findViewById(R.id.tv_anxiety_and_sleep_desc_2)
        val textHeading2: TextView = itemView.findViewById(R.id.tv_anxiety_and_sleep_heading_1)
    }



}
