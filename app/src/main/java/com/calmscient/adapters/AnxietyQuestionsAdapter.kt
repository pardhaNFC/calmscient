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
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.AnxietyTextDataClass

class AnxietyQuestionsAdapter(private val items: MutableList<AnxietyTextDataClass>) :
    RecyclerView.Adapter<AnxietyQuestionsAdapter.AnxietyQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnxietyQuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.manage_anxietyquestions_items, parent, false)
        return AnxietyQuestionViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: AnxietyQuestionViewHolder, position: Int) {
        val item = items[position]
        // Check and set text1
        if (item.text1.isNullOrBlank()) {
            holder.text1.visibility = View.GONE
        } else {
            holder.text1.text = item.text1
            holder.text1.visibility = View.VISIBLE
        }
        // Check and set text2
        if (item.text2.isNullOrBlank()) {
            holder.text2.visibility = View.GONE
        } else {
            holder.text2.text = item.text2
            holder.text2.visibility = View.VISIBLE
        }

        // Set anxietybulb and imageanxiety unconditionally
       // item.anxietybulb?.let { holder.anxietybulb.setImageResource(it) }
        item.imageanxiety?.let { holder.imageanxiety.setImageResource(it) }

        // Check and set text3
        if (item.text3.isNullOrBlank()) {
            holder.text3.visibility = View.GONE
        } else {
            holder.text3.text = item.text3
            holder.text3.visibility = View.VISIBLE
        }

        // Check and set text4
        if (item.text4.isNullOrBlank()) {
            holder.text4.visibility = View.GONE
        } else {
            holder.text4.text = item.text4
            holder.text4.visibility = View.VISIBLE
        }
        // Check and set text5
        if (item.text5.isNullOrBlank()) {
            holder.text5.visibility = View.GONE
        } else {
            holder.text5.text = item.text5
            holder.text5.visibility = View.VISIBLE
        }

        // Check and set text6
        if (item.text6.isNullOrBlank()) {
            holder.text6.visibility = View.GONE
        } else {
            holder.text6.text = item.text6
            holder.text6.visibility = View.VISIBLE
        }

        // Check and set text7
        if (item.text7.isNullOrBlank()) {
            holder.text7.visibility = View.GONE
        } else {
            if(item.text7 =="Here is how navigating that stressful situation might look like:")
            {
                holder.text7.setTextColor(R.color.black)
                holder.text7.text = item.text7
            }
            else
            {
                holder.text7.text = item.text7
            }
            holder.text7.visibility = View.VISIBLE
        }
    }

    inner class AnxietyQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text1)
        val text2: TextView = itemView.findViewById(R.id.text2)
        //val anxietybulb: ImageView = itemView.findViewById(R.id.anxietybulb)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageanxiety)
        val text3: TextView = itemView.findViewById(R.id.text3)
        val text4: TextView = itemView.findViewById(R.id.text4)
        val text5: TextView = itemView.findViewById(R.id.text5)
        val text6: TextView = itemView.findViewById(R.id.text6)
        val text7: TextView = itemView.findViewById(R.id.text7)
    }
}