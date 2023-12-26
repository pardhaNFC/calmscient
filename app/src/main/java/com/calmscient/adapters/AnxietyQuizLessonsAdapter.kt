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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.CommonDialog
import com.calmscient.di.remote.AnxietyLessonsQuizDataClass

class AnxietyQuizLessonsAdapter(private val questionsList: List<AnxietyLessonsQuizDataClass>) :
    RecyclerView.Adapter<AnxietyQuizLessonsAdapter.AnxietyQuizLessonsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnxietyQuizLessonsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_quiz_lessons3_2, parent, false
            )
        return AnxietyQuizLessonsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    override fun onBindViewHolder(holder: AnxietyQuizLessonsViewHolder, position: Int) {
        val questionItem = questionsList[position]

        holder.question.text = questionItem.question.get(position)
        holder.bindOptions(questionItem.options, questionItem.selectedOption)
    }

    inner class AnxietyQuizLessonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.tv_question)
        val optionsRecyclerView: RecyclerView =
            itemView.findViewById(R.id.optionsQuestionsRecyclerView)

        init {
            optionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        }

        fun bindOptions(options: List<String>, selectedOptionIndex: Int) {
            val optionsAdapter = OptionsAdapter(options, selectedOptionIndex) { clickedPosition ->
                questionsList[adapterPosition].selectedOption = clickedPosition
                notifyDataSetChanged()
            }
            optionsRecyclerView.adapter = optionsAdapter
        }
    }

}