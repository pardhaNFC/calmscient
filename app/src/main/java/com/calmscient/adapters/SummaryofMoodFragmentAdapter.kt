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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.data.remote.WeeklySummaryMoodTask
import com.calmscient.utils.AnimationUtils
class SummaryofMoodFragmentAdapter (private val allTasks: MutableList<WeeklySummaryMoodTask>) :
    RecyclerView.Adapter<SummaryofMoodFragmentAdapter.TaskViewHolder>() {

    // Store a reference to the currently expanded card position
    private var expandedCardPosition: Int = -1

    fun updateTasks(newTasks: List<WeeklySummaryMoodTask>) {
        allTasks.clear()
        allTasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SummaryofMoodFragmentAdapter.TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weeklysummary_item_card_screens, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SummaryofMoodFragmentAdapter.TaskViewHolder, @SuppressLint(
        "RecyclerView"
    ) position: Int) {
        holder.bind(allTasks[position])

        // Set an OnClickListener to handle expanding/collapsing
        holder.titleCardView.setOnClickListener {
            if (expandedCardPosition == position) {
                // Clicked on an already expanded card, so collapse it
                holder.collapse()
                expandedCardPosition = -1
            } else {
                // Clicked on a different card, collapse the previously expanded card (if any)
                val previouslyExpandedCardPosition = expandedCardPosition
                if (previouslyExpandedCardPosition != -1) {
                    notifyItemChanged(previouslyExpandedCardPosition)
                }

                // Expand the clicked card
                holder.expand()
                expandedCardPosition = position
            }
        }

        // Check if the current card should be expanded or collapsed based on its position
        if (expandedCardPosition == position) {
            holder.expand()
        } else {
            holder.collapse()
        }
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    inner class TaskViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // ...
        // (Other code remains the same)
        private var isExpanded = false
        val dateView1: TextView = itemView.findViewById(R.id.dateView1)
        val dropDownImage: ImageView = itemView.findViewById(R.id.dropdownButton)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val titleCardView: ConstraintLayout = itemView.findViewById(R.id.taskTitleLayout)

        init {
            // Set the initial visibility to GONE
            textView1.visibility = View.GONE
            textView2.visibility = View.GONE
            // Set an OnClickListener to handle expanding/collapsing
            titleCardView.setOnClickListener {
                if (isExpanded)
                    collapse()
                else
                    expand()
            }
            // All tasks should be collapsed by default
            collapse()
        }
        fun bind(task: WeeklySummaryMoodTask) {
            dateView1.text = task.dateView1
            textView1.text = task.textView1
            textView2.text = task.textView2
        }
        fun expand() {
            AnimationUtils.expand(textView1)
            dropDownImage.setImageResource(R.drawable.minus)
        }

        fun collapse() {
            AnimationUtils.collapse(textView1)
            dropDownImage.setImageResource(R.drawable.ic_expand)
        }
    }
}