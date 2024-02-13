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

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.ReviewTypeRestructureActivity
import com.calmscient.di.remote.AnxietyRestructureDataClass
import com.calmscient.di.remote.Task


class AdapterRestructureBinding(
    val context: Context,
    private val items: MutableList<AnxietyRestructureDataClass>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_TYPE_A = 0
    }

    val VIEW_TYPE_TYPE_A: Int = 0
    val VIEW_TYPE_TYPE_B: Int = 1
    val VIEW_TYPE_TYPE_C: Int = 2
    val VIEW_TYPE_TYPE_D: Int = 3
    val VIEW_TYPE_TYPE_E: Int = 4
    val VIEW_TYPE_TYPE_F: Int = 5
    val VIEW_TYPE_TYPE_EXPAND_E: Int = 6
    val VIEW_TYPE_TYPE_G: Int = 7
    val VIEW_TYPE_TYPE_H: Int = 8
    val VIEW_TYPE_TYPE_I: Int = 9
    val VIEW_TYPE_TYPE_J: Int = 10
    val VIEW_TYPE_TYPE_K: Int = 11

    private val taskWorkData = mutableListOf<Task>()
    var expandAdapter = ExpandFiveRestructureAdapter(mutableListOf())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TYPE_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_restructure_one, parent, false)
                TypeViewHolderA(view)
            }

            VIEW_TYPE_TYPE_B -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_restructure_two, parent, false)
                TypeViewHolderScreenTwo(view)
            }

            VIEW_TYPE_TYPE_C -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_restructure_three, parent, false)
                TypeViewHolderScreenThree(view)
            }

            VIEW_TYPE_TYPE_D -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_four, parent, false
                    )
                TypeViewHolderScreenFour(view)
            }

            VIEW_TYPE_TYPE_E -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_five, parent, false
                    )
                TypeViewHolderScreenFive(view)
            }

            VIEW_TYPE_TYPE_EXPAND_E -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_five_expand, parent, false
                    )
                TypeViewHolderScreenFiveExpand(view)
            }

            VIEW_TYPE_TYPE_F -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_six, parent, false
                    )
                TypeViewHolderScreenSix(view)
            }

            VIEW_TYPE_TYPE_G -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_seven, parent, false
                    )
                TypeViewHolderScreenSeven(view)
            }

            VIEW_TYPE_TYPE_H -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_eight, parent, false
                    )
                TypeViewHolderScreenEight(view)
            }

            VIEW_TYPE_TYPE_I -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_nine, parent, false
                    )
                TypeViewHolderScreenNine(view)
            }

            VIEW_TYPE_TYPE_J -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_ten, parent, false
                    )
                TypeViewHolderScreenTen(view)
            }

            VIEW_TYPE_TYPE_K -> {
                val view = LayoutInflater.from(
                    parent.context
                )
                    .inflate(
                        R.layout.layout_restructure_eleven, parent, false
                    )
                TypeViewHolderScreenEleven(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    class TypeViewHolderA(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderScreenTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }*/
        //val checkboxText1: TextView = itemView.findViewById(R.id.custom_checkbox_1)

        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.optionOne),
                itemView.findViewById(R.id.optionTwo),
                itemView.findViewById(R.id.optionThree),
                itemView.findViewById(R.id.optionFour),
                itemView.findViewById(R.id.optionFive),
                itemView.findViewById(R.id.optionSix),
                itemView.findViewById(R.id.optionSeven),
                itemView.findViewById(R.id.optionEight),
                itemView.findViewById(R.id.optionNine),
                itemView.findViewById(R.id.optionTen),
                itemView.findViewById(R.id.optionEleven)
            ),
        )


        init {
            // Set OnClickListener for each set
            optionSets.forEachIndexed { setIndex, set ->
                set.forEachIndexed { optionIndex, textView ->
                    textView.setOnClickListener {
                        onOptionClicked(setIndex, optionIndex)
                    }
                }
            }
        }

        private val selectedOptionIndices: MutableList<Int> = MutableList(optionSets.size)
        {
            -1
        }

        private fun onOptionClicked(setIndex: Int, selectedIndex: Int) {
            if (selectedIndex != selectedOptionIndices[setIndex]) {
                selectedOptionIndices[setIndex] = selectedIndex
                clearSelection(setIndex)
                selectOption(setIndex, selectedIndex)
            }
        }

        private fun clearSelection(setIndex: Int) {
            optionSets[setIndex].forEach { textView ->
                textView.setBackgroundResource(R.drawable.card_default_background)
                textView.setTextColor(Color.parseColor("#424242"))
            }
        }

        private fun selectOption(setIndex: Int, selectedIndex: Int) {
            optionSets[setIndex][selectedIndex].setBackgroundResource(R.drawable.card_selected_background)
            optionSets[setIndex][selectedIndex].setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    class TypeViewHolderScreenThree(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
        }

        val checkboxText1: TextView = itemView.findViewById(R.id.custom_checkbox_1)
        val btnReviewType: Button = itemView.findViewById(R.id.btnReviewTypes)
    }

    class TypeViewHolderScreenFour(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderScreenFive(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderScreenFiveExpand(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
        }

        val expandRecyclerview: RecyclerView =
            itemView.findViewById(R.id.expandRestructureRecyclerView)

    }

    class TypeViewHolderScreenSix(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderScreenSeven(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderScreenEight(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
        }
    }

    class TypeViewHolderScreenNine(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemsList: AnxietyRestructureDataClass) {
        }
    }

    class TypeViewHolderScreenEleven(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
        }

    }

    class TypeViewHolderScreenTen(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(
                    R.id.optionJob
                ),
                itemView.findViewById(
                    R.id.optionHealth
                ),
                itemView.findViewById(
                    R.id.optionParenting
                )
            ),
            listOf
                (
                itemView.findViewById(
                    R.id.optionPerfectionistThinking
                ),
                itemView.findViewById(
                    R.id.optionNegativeFiltering
                ),
                itemView.findViewById(
                    R.id.optionParentingJumping
                )
            ),
            listOf
                (
                itemView.findViewById(
                    R.id.optionPerfectionistThinking_5
                ),
                itemView.findViewById(
                    R.id.optionNegativeFiltering_5
                ),
                itemView.findViewById(
                    R.id.optionParentingJumping_5
                )
            ),
            listOf
                (
                itemView.findViewById(
                    R.id.optionPerfectionistThinking_6
                ),
                itemView.findViewById(
                    R.id.optionNegativeFiltering_6
                ),
                itemView.findViewById(
                    R.id.optionParentingJumping_6
                )
            ),
            // Add more sets as needed
        )
        private val selectedOptionIndices: MutableList<Int> = MutableList(optionSets.size)
        {
            -1
        }

        init {
            // Set OnClickListener for each set
            optionSets.forEachIndexed { setIndex, set ->
                set.forEachIndexed { optionIndex, textView ->
                    textView.setOnClickListener {
                        onOptionClicked(setIndex, optionIndex)
                    }
                }
            }
        }

        private fun onOptionClicked(setIndex: Int, selectedIndex: Int) {
            if (selectedIndex != selectedOptionIndices[setIndex]) {
                selectedOptionIndices[setIndex] = selectedIndex
                clearSelection(setIndex)
                selectOption(setIndex, selectedIndex)
            }
        }

        private fun clearSelection(setIndex: Int) {
            optionSets[setIndex].forEach { textView ->
                textView.setBackgroundResource(R.drawable.card_default_background)
                textView.setTextColor(Color.parseColor("#424242"))
            }
        }

        private fun selectOption(setIndex: Int, selectedIndex: Int) {
            optionSets[setIndex][selectedIndex].setBackgroundResource(R.drawable.card_selected_background)
            optionSets[setIndex][selectedIndex].setTextColor(Color.parseColor("#FFFFFF"))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeViewHolderA -> {
                holder.bind(items[position])
            }

            is TypeViewHolderScreenTwo -> {
                //holder.bind(items[position])
                /* if (holder.includedLayout1.visibility == View.VISIBLE) {
                     holder.checkboxText1.text = context.getString(R.string.anxiety_restructure_screen2_checkOne)
                 } else if (holder.includedLayout2.visibility == View.VISIBLE) {
                     //holder.checkboxText1.setText("Job security or performance")
                     holder.checkboxText1.text = context.getString(R.string.anxiety_restructure_screen2_checkTwo)
                 }*/

            }

            is TypeViewHolderScreenThree -> {
                holder.bind(items[position])
                holder.btnReviewType.setOnClickListener {
                    context.startActivity(
                        Intent(
                            context,
                            ReviewTypeRestructureActivity::class.java
                        )
                    )
                }
                holder.checkboxText1.setOnClickListener {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            }

            is TypeViewHolderScreenFiveExpand -> {
                holder.bind(items[position])
                addTaskData()
                holder.expandRecyclerview.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                expandAdapter = ExpandFiveRestructureAdapter(taskWorkData)
                holder.expandRecyclerview.adapter = expandAdapter
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].theViewType
    }

    private fun addTaskData() {
        val tasks = listOf(
            Task(
                "A",
                context.getString(R.string.all_summary_title),
                context.getString(R.string.all_summary)
            ),
            Task(
                "C",
                context.getString(R.string.anxiety_biased_card_catas),
                context.getString(R.string.Catastrophizing_summary)
            ),
            Task(
                "E",
                context.getString(R.string.anxiety_biased_card_emotional),
                context.getString(R.string.Emotional_summary),
            ),
            Task(
                "J",
                context.getString(R.string.anxiety_biased_card_jumping),
                context.getString(R.string.Jumping_summary)
            ),
            Task(
                "M",
                context.getString(R.string.anxiety_biased_card_making),
                context.getString(R.string.Making_summary)
            ),
            Task(
                "N",
                context.getString(R.string.anxiety_biased_card_negative),
                context.getString(R.string.Negative_summary)
            ),
            Task(
                "P",
                context.getString(R.string.anxiety_biased_card_perfect),
                context.getString(R.string.Perfectionist_summary)
            ),

            Task(
                "P",
                context.getString(R.string.anxiety_biased_card_personalizeBlame),
                context.getString(R.string.Personalization_summary)
            ),

        )
        taskWorkData.addAll(tasks)
        expandAdapter.notifyDataSetChanged()
    }
}