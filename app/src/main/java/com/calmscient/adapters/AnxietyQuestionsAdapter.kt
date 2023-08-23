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
import com.calmscient.activities.AnxietyTextDataClass
class AnxietyQuestionsAdapter (private val items: List<AnxietyTextDataClass>) :
    RecyclerView.Adapter<AnxietyQuestionsAdapter.AnxietyQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnxietyQuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.manage_anxietyquestions_items, parent, false)
        return AnxietyQuestionViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AnxietyQuestionViewHolder, position: Int) {
        val item = items[position]

        holder.text1.text = item.text1
        holder.text2.text = item.text2
        item.anxietybulb?.let { holder.anxietybulb.setImageResource(it) }
        item.imageanxiety?.let { holder.imageanxiety.setImageResource(it) }
        holder.text3.text = item.text3
        holder.text4.text = item.text4
    }

    inner class AnxietyQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text1)
        val text2: TextView = itemView.findViewById(R.id.text2)
        val anxietybulb: ImageView = itemView.findViewById(R.id.anxietybulb)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageanxiety)
        val text3: TextView = itemView.findViewById(R.id.text3)
        val text4: TextView = itemView.findViewById(R.id.text4)
    }
}