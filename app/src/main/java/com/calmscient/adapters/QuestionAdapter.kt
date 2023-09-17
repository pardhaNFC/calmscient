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
import com.calmscient.activities.CommonDialog
import com.calmscient.fragments.Question

class QuestionAdapter(private val context: Context, private val questions: List<Question>, val title: String) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.questions_item_card_view, parent, false)
        return QuestionViewHolder(itemView)
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

            infoIcon.setOnClickListener {
                val commonDialog = CommonDialog(context)

                // Show a dialog when the fragment is loaded
                if (title == context.getString(R.string.phq_heading)) {
                    commonDialog.showDialog(context.getString(R.string.phq))
                } else if (title == context.getString(R.string.gad_heading)) {
                    commonDialog.showDialog(context.getString(R.string.gad))
                } else if (title == context.getString(R.string.audit_heading)) {
                    commonDialog.showDialog(context.getString(R.string.audit))
                } else if (title == context.getString(R.string.dast_heading)) {
                    commonDialog.showDialog(context.getString(R.string.dast))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = questions[position]
        holder.questionTextView.text = item.questionText
        if (position == 0) {
            if (title == "PHQ-9") {
                holder.descTextView.visibility = View.GONE
                holder.infoIcon.visibility = View.VISIBLE
                holder.descTextView.text = context.getString(R.string.phq)
            } else if (title == "GAD-7") {
                holder.descTextView.visibility = View.GONE
                holder.infoIcon.visibility = View.VISIBLE
                holder.descTextView.text = context.getString(R.string.gad)
            } else if (title == "AUDIT") {
                holder.descTextView.visibility = View.GONE
                holder.infoIcon.visibility = View.VISIBLE
                holder.descTextView.text = context.getString(R.string.audit)
            } else if (title == "DAST-10") {
                holder.descTextView.visibility = View.GONE
                holder.infoIcon.visibility = View.VISIBLE
                holder.descTextView.text = context.getString(R.string.dast)
            }
        } else {
            holder.descTextView.visibility = View.GONE
            holder.infoIcon.visibility = View.GONE
        }

        Log.d("AdapterDebug", "Options size: ${item.options.size}")

        holder.bindOptions(item.options, item.selectedOption)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

}