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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.di.remote.StressTriggerDataClass
import com.calmscient.fragments.FamilyRelatedStressQuizFragment
import com.calmscient.fragments.PersonalHealthStressQuizFragment
import com.calmscient.fragments.SocialRelationshipStressQuizFragment
import com.calmscient.fragments.WorkRelatedStressQuizFragment

class YourStressTriggersQuizAdapter(private val items: List<StressTriggerDataClass>) :
    RecyclerView.Adapter<YourStressTriggersQuizAdapter.StressTriggerCardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YourStressTriggersQuizAdapter.StressTriggerCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stress_trigger_itemcard, parent, false)
        return StressTriggerCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: StressTriggerCardViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        item.nextImageResource?.let { holder.stressTriggerImageNext.setImageResource(it) }
        // item.keyImageResource?.let { holder.imageViewMoon.setImageResource(it) }
        /*holder.cardViewLayout.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val fragment = QuestionFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, fragment).addToBackStack(null).commit()
        }*/
        holder.cardViewLayout.setOnClickListener {
            val activity = holder.itemView.context as AppCompatActivity
            when (position) {
                0 -> {
                    val fragment = WorkRelatedStressQuizFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                1 -> {
                    val fragment = PersonalHealthStressQuizFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()
                    //Toast.makeText(activity, "Coming soon", Toast.LENGTH_SHORT).show()
                }

                2 -> {
                    val fragment = FamilyRelatedStressQuizFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()

                   // Toast.makeText(activity, "Coming soon", Toast.LENGTH_SHORT).show()
                }

                3 -> {
                    val fragment = SocialRelationshipStressQuizFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, fragment)
                        .addToBackStack(null)
                        .commit()

                    //Toast.makeText(activity, "Coming soon", Toast.LENGTH_SHORT).show()
                }
                // Add more cases for other card positions if needed
                else -> {
                    // Handle click for other cards
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class StressTriggerCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleText)
        val cardViewLayout: CardView = itemView.findViewById(R.id.stress_trigger_item_card_view)
        val stressTriggerImageNext: ImageView = itemView.findViewById(R.id.stress_trigger_next_icon)


    }
}