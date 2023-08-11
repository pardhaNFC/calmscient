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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.Question
class QuestionAdapter (private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.questions_item_card_view, parent, false)
        return QuestionViewHolder(itemView)
    }
    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        val optionsRecyclerView: RecyclerView = itemView.findViewById(R.id.optionsRecyclerView)

        init {
            optionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        }
        fun bindOptions(options: List<String>, selectedOptionIndex: Int) {
            val optionsAdapter = OptionsAdapter(options, selectedOptionIndex) { clickedPosition ->
                questions[adapterPosition].selectedOption = clickedPosition
                notifyDataSetChanged()
            }
            optionsRecyclerView.adapter = optionsAdapter
        }
    }
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = questions[position]
        holder.questionTextView.text = item.questionText
        Log.d("AdapterDebug", "Options size: ${item.options.size}")

        holder.bindOptions(item.options, item.selectedOption)
    }
    override fun getItemCount(): Int {
        return questions.size
    }

}