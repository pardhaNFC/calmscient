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
import com.calmscient.di.remote.AnxietyBiasedDataClass

class BiasedThinkingAdapter(val items: MutableList<AnxietyBiasedDataClass>) :
    RecyclerView.Adapter<BiasedThinkingAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_biased_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text_biased_1)
        val text2: TextView = itemView.findViewById(R.id.text_biased_2)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageAnxietyBiased1)
        val text3: TextView = itemView.findViewById(R.id.text_biased_3)
        fun bind(biasedItems: AnxietyBiasedDataClass) {
            text1.text = biasedItems.text1
            biasedItems.imageanxiety?.let { imageanxiety.setImageResource(it) }
            text2.text = biasedItems.text2
            text3.text = biasedItems.text3
        }
    }

}
