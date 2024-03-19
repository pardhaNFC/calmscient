package com.calmscient.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.Question

class YourStressTriggerQuizAdapter(private val context: Context, private val questions: List<Question>, val title: String) :
    RecyclerView.Adapter<YourStressTriggerQuizAdapter.YourStressTriggerQuizHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourStressTriggerQuizHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.questions_item_card_view, parent, false)
        return YourStressTriggerQuizHolder(itemView)
    }

    inner class YourStressTriggerQuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        val optionsRecyclerView: RecyclerView = itemView.findViewById(R.id.optionsRecyclerView)
        val descTextView: TextView = itemView.findViewById(R.id.desc_text_view)
        val infoIcon: ImageView = itemView.findViewById(R.id.informationDialogButton)

        init {
            optionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        }

        fun bindOptions(options: List<String>, selectedOptionIndex: Int) {
            val optionsAdapter = OptionsAdapter(options, selectedOptionIndex) { clickedPosition ->
                questions[adapterPosition].selectedOption = clickedPosition
                notifyDataSetChanged()
            }
            optionsRecyclerView.adapter = optionsAdapter
            //descTextView.visibility = if (isFirstItem) View.VISIBLE else View.GONE


        }
    }

    override fun onBindViewHolder(holder: YourStressTriggerQuizHolder, position: Int) {
        val item = questions[position]
        holder.questionTextView.text = item.questionText

        holder.descTextView.visibility = View.GONE
        holder.infoIcon.visibility = View.GONE


        holder.bindOptions(item.options, item.selectedOption)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

}