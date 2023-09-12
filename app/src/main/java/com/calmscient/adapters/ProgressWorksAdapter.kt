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
import com.calmscient.data.remote.ProgressWorksTask
import com.calmscient.data.remote.WeeklySummaryMoodTask
import com.calmscient.utils.AnimationUtils
class ProgressWorksAdapter (private val allTasks: MutableList<ProgressWorksTask>) :
    RecyclerView.Adapter<ProgressWorksAdapter.TaskViewHolder>() {
    fun updateTasks(newTasks: List<ProgressWorksTask>) {
        allTasks.clear()
        allTasks.addAll(newTasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgressWorksAdapter.TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.progressonwork_itemcard, parent, false)
        return TaskViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ProgressWorksAdapter.TaskViewHolder, position: Int) {
        holder.bind(allTasks[position])
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    inner class TaskViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var isExpanded = false
        val titleView: TextView = itemView.findViewById(R.id.titleView)
        val dropDownImage: ImageView = itemView.findViewById(R.id.dropdownButton)
        val titlePercentage: TextView = itemView.findViewById(R.id.titlePercentage)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
        val textView4: TextView = itemView.findViewById(R.id.textView4)
        val textView5: TextView = itemView.findViewById(R.id.textView5)
        val textView6: TextView = itemView.findViewById(R.id.textView6)
        val textView7: TextView = itemView.findViewById(R.id.textView7)
        val textView8: TextView = itemView.findViewById(R.id.textView8)
        val taskTitleLayout: View = itemView.findViewById(R.id.taskTitleLayout)

        init {
            // Set the initial visibility to GONE
            textView1.visibility = View.GONE
            textView2.visibility = View.GONE
            textView3.visibility = View.GONE
            textView4.visibility = View.GONE
            textView5.visibility = View.GONE
            textView6.visibility = View.GONE
            textView7.visibility = View.GONE
            textView8.visibility = View.GONE
            // Set an OnClickListener to handle expanding/collapsing
            taskTitleLayout.setOnClickListener {
                if (isExpanded)
                    collapse()
                else
                    expand()
            }
            // All tasks should be collapsed by default
            collapse()
        }

        fun bind(task: ProgressWorksTask) {
            titleView.text = task.titleView
            titlePercentage.text = task.titlePercentage
            textView1.text = task.textView1
            textView2.text = task.textView2
            textView3.text = task.textView3
            textView4.text = task.textView4
            textView5.text = task.textView5
            textView6.text = task.textView6
            textView7.text = task.textView7
            textView8.text = task.textView8
        }

        private fun expand() {
            AnimationUtils.expand(textView1)
            AnimationUtils.expand(textView2)
            AnimationUtils.expand(textView3)
            AnimationUtils.expand(textView4)
            AnimationUtils.expand(textView5)
            AnimationUtils.expand(textView6)
            AnimationUtils.expand(textView7)
            AnimationUtils.expand(textView8)
            dropDownImage.setImageResource(R.drawable.minus)
            isExpanded = true
        }
        private fun collapse() {
            AnimationUtils.collapse(textView1)
            AnimationUtils.collapse(textView2)
            AnimationUtils.collapse(textView3)
            AnimationUtils.collapse(textView4)
            AnimationUtils.collapse(textView5)
            AnimationUtils.collapse(textView6)
            AnimationUtils.collapse(textView7)
            AnimationUtils.collapse(textView8)
            dropDownImage.setImageResource(R.drawable.ic_expand)
            isExpanded = false
        }
    }
}