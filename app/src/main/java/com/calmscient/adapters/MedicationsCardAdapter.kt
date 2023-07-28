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
import com.calmscient.fragments.CardViewItem

class MedicationsCardAdapter(private val items: List<CardViewItem>) : RecyclerView.Adapter<MedicationsCardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medications_item_card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        holder.dosageTextView.text = item.dosage
        holder.timeMorningTextView.text = item.timeMorning
        holder.timeEveningTextView.text = item.timeEvening
        item.sunImageResource?.let { holder.imageViewSun.setImageResource(it) }
        item.moonImageResource?.let { holder.imageViewMoon.setImageResource(it) }
    }

    override fun getItemCount(): Int = items.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val dosageTextView: TextView = itemView.findViewById(R.id.dosageTextView)
        val timeMorningTextView: TextView = itemView.findViewById(R.id.timeMorningTextView)
        val timeEveningTextView: TextView = itemView.findViewById(R.id.timeEveningTextView)
        val imageViewSun: ImageView = itemView.findViewById(R.id.imageViewSun)
        val imageViewMoon: ImageView = itemView.findViewById(R.id.imageViewMoon)

    }
}
