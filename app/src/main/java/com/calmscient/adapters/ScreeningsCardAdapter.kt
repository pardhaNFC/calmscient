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
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.AUDITQuestionFragment
import com.calmscient.fragments.DASTQuestionFragment
import com.calmscient.fragments.GADQuestionFragment
import com.calmscient.fragments.HistoryFragment
import com.calmscient.fragments.QuestionFragment
import com.calmscient.fragments.ScreeningsCardItem

class ScreeningsCardAdapter(private val items: List<ScreeningsCardItem>) :
    RecyclerView.Adapter<ScreeningsCardAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScreeningsCardAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.screenings_item_card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        item.historyImageResource?.let { holder.imageHistory.setImageResource(it) }
        item.nextOrKeyImageResource?.let { holder.screeningsImageNext.setImageResource(it) }
        // item.keyImageResource?.let { holder.imageViewMoon.setImageResource(it) }
        /*holder.cardViewLayout.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val fragment = QuestionFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, fragment).addToBackStack(null).commit()
        }*/
        holder.imageHistory.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            //val fragment = ResultsFragment()
            val fragment = HistoryFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, fragment).addToBackStack(null).commit()
        }
        holder.cardViewLayout.setOnClickListener {
            val activity = holder.itemView.context as AppCompatActivity
            when (position) {
                0 -> {
                    val fragment = QuestionFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                1 -> {
                    val fragment = GADQuestionFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                2 -> {
                    val fragment = AUDITQuestionFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                3 -> {
                    val fragment = DASTQuestionFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                // Add more cases for other card positions if needed
                else -> {
                    // Handle click for other cards
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleText)
        val cardViewLayout: CardView = itemView.findViewById(R.id.screenings_item_card_view)
        val imageHistory: ImageView = itemView.findViewById(R.id.history_icon)
        val screeningsImageNext: ImageView = itemView.findViewById(R.id.screenings_next_icon)


    }
}