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
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.di.remote.MakingConnectionDataClass
import com.calmscient.di.remote.Task
import com.calmscient.fragments.MakingConnectionFragment

class MakingConnectionAdapter (
    val context: Context,
    private val items: MutableList<MakingConnectionDataClass>,
    makingConnectionFragment: MakingConnectionFragment
) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_TYPE_A = 0
    }

    val VIEW_TYPE_TYPE_A: Int = 0
    val VIEW_TYPE_TYPE_B: Int = 1
    val VIEW_TYPE_TYPE_C: Int = 2
    val VIEW_TYPE_TYPE_D: Int = 3
    val VIEW_TYPE_TYPE_E: Int = 4
    val VIEW_TYPE_TYPE_F: Int = 5
    val VIEW_TYPE_TYPE_G: Int = 6
    val VIEW_TYPE_TYPE_H: Int = 7
    val VIEW_TYPE_TYPE_I: Int = 8
    val VIEW_TYPE_TYPE_J: Int = 9
    val VIEW_TYPE_TYPE_J_1: Int = 10
    val VIEW_TYPE_TYPE_J_2: Int = 11
    val VIEW_TYPE_TYPE_J_3: Int = 12
    val VIEW_TYPE_TYPE_J_4: Int = 13
    val VIEW_TYPE_TYPE_K: Int = 14
    val VIEW_TYPE_TYPE_K_1: Int = 15
    val VIEW_TYPE_TYPE_K_2: Int = 16
    val VIEW_TYPE_TYPE_K_3: Int = 17
    val VIEW_TYPE_TYPE_K_4: Int = 18

    private val taskWorkData = mutableListOf<Task>()
    var expandAdapter = ExpandFiveRestructureAdapter(mutableListOf())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TYPE_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_makingconnection_one, parent, false)
                TypeViewHolderA(view)
            }
            VIEW_TYPE_TYPE_B -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_makingconnection_two, parent, false)
                TypeViewHolderTwo(view)
            }
            VIEW_TYPE_TYPE_C -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_makingconnection_three, parent, false)
                TypeViewHolderThree(view)
            }
            VIEW_TYPE_TYPE_D -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_four, parent, false)
                TypeViewHolderFour(view)
            }
            VIEW_TYPE_TYPE_E -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_five, parent, false)
                TypeViewHolderFive(view)
            }
            VIEW_TYPE_TYPE_F -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_six, parent, false)
                TypeViewHolderSix(view)
            }
            VIEW_TYPE_TYPE_G -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_seven, parent, false)
                TypeViewHolderSeven(view)
            }
            VIEW_TYPE_TYPE_H -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_eight, parent, false)
                TypeViewHolderEight(view)
            }
            VIEW_TYPE_TYPE_I -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_nine, parent, false)
                TypeViewHolderNine(view)
            }
            VIEW_TYPE_TYPE_J -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_1_screen_1, parent, false)
                TypeViewHolderTen(view)
            }
            VIEW_TYPE_TYPE_J_1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_1_screen_2, parent, false)
                TypeViewHolderTenOne(view)
            }
            VIEW_TYPE_TYPE_J_2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_1_screen_3, parent, false)
                TypeViewHolderTenTwo(view)
            }
            VIEW_TYPE_TYPE_J_3 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_1_screen_4, parent, false)
                TypeViewHolderTenThree(view)
            }
            VIEW_TYPE_TYPE_J_4 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_1_screen_5, parent, false)
                TypeViewHolderTenFour(view)
            }
            VIEW_TYPE_TYPE_K -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_2_screen_1, parent, false)
                TypeViewHolderScenarioTwoScreenOne(view)
            }
            VIEW_TYPE_TYPE_K_1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_2_screen_2, parent, false)
                TypeViewHolderScenarioTwoScreenTwo(view)
            }
            VIEW_TYPE_TYPE_K_2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_2_screen_3, parent, false)
                TypeViewHolderScenarioTwoScreenThree(view)
            }
            VIEW_TYPE_TYPE_K_3 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_2_screen_4, parent, false)
                TypeViewHolderScenarioTwoScreenFour(view)
            }
            VIEW_TYPE_TYPE_K_4 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.calmscient.R.layout.layout_makingconnection_ten_scenario_2_screen_5, parent, false)
                TypeViewHolderScenarioTwoScreenFive(view)
            }



            else -> throw IllegalArgumentException("Invalid view type")
        }
    }



    class TypeViewHolderA(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
        val playPauseButton : ImageView = itemView.findViewById(R.id.playButton)
    }
    class TypeViewHolderTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }
    class TypeViewHolderThree(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }
    class TypeViewHolderFour(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noraCard: ImageView = itemView.findViewById(R.id.nora)
        fun bind(itemsList: MakingConnectionDataClass) {

            //need to implement the onclick listener
        }

    }

    class TypeViewHolderFive(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        val text1: TextView = itemView.findViewById(R.id.option_1_1)
        val text2: TextView = itemView.findViewById(R.id.option_1_2)

        val text_2_1: TextView = itemView.findViewById(R.id.option_2_1)
        val text_2_2: TextView = itemView.findViewById(R.id.option_2_2)

        val savebutton : AppCompatButton = itemView.findViewById(R.id.btn_save_making_connection)

        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4)
            ),
            listOf
                (
                itemView.findViewById(R.id.option_2_1),
                itemView.findViewById(R.id.option_2_2),
                itemView.findViewById(R.id.option_2_3),
                itemView.findViewById(R.id.option_2_4)
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
    class TypeViewHolderSix(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
    }
    class TypeViewHolderSeven(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        val text1: TextView = itemView.findViewById(R.id.option_1_1)
        val text2: TextView = itemView.findViewById(R.id.option_1_2)



        val text2_1: TextView = itemView.findViewById(R.id.option_2_1)
        val text2_2: TextView = itemView.findViewById(R.id.option_2_2)

        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4)
            ),
            listOf
                (
                itemView.findViewById(R.id.option_2_1),
                itemView.findViewById(R.id.option_2_2),
                itemView.findViewById(R.id.option_2_3),
                itemView.findViewById(R.id.option_2_4)
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

    class TypeViewHolderEight(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
    }

    class TypeViewHolderNine(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
    }
    class TypeViewHolderTen(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
    }
    class TypeViewHolderTenOne(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4)
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
    class TypeViewHolderTenTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4),
                itemView.findViewById(R.id.option_1_5),
                itemView.findViewById(R.id.option_1_6),
                itemView.findViewById(R.id.option_1_7),
                itemView.findViewById(R.id.option_1_8),
                itemView.findViewById(R.id.option_1_9),
                itemView.findViewById(R.id.option_1_10),
                itemView.findViewById(R.id.option_1_11),
                itemView.findViewById(R.id.option_1_12),
                itemView.findViewById(R.id.option_1_13),
                itemView.findViewById(R.id.option_1_14),
                itemView.findViewById(R.id.option_1_15),
                itemView.findViewById(R.id.option_1_16),
                itemView.findViewById(R.id.option_1_17),
                itemView.findViewById(R.id.option_1_18),
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
    class TypeViewHolderTenThree(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }

        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4),
                itemView.findViewById(R.id.option_1_5),
                itemView.findViewById(R.id.option_1_6),
                itemView.findViewById(R.id.option_1_7),
                itemView.findViewById(R.id.option_1_8),
                itemView.findViewById(R.id.option_1_9),
                itemView.findViewById(R.id.option_1_10)
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
    class TypeViewHolderTenFour(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        var saveBtn : AppCompatButton = itemView.findViewById(R.id.yesButton)
        var savedBtn : AppCompatButton = itemView.findViewById(R.id.savedButton)

    }
    class TypeViewHolderScenarioTwoScreenOne(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
    }
    class TypeViewHolderScenarioTwoScreenTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4),

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
    class TypeViewHolderScenarioTwoScreenThree(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4),
                itemView.findViewById(R.id.option_1_5),
                itemView.findViewById(R.id.option_1_6),
                itemView.findViewById(R.id.option_1_7),
                itemView.findViewById(R.id.option_1_8),
                itemView.findViewById(R.id.option_1_9),
                itemView.findViewById(R.id.option_1_10),
                itemView.findViewById(R.id.option_1_11),
                itemView.findViewById(R.id.option_1_12),
                itemView.findViewById(R.id.option_1_13),
                itemView.findViewById(R.id.option_1_14),
                itemView.findViewById(R.id.option_1_15),
                itemView.findViewById(R.id.option_1_16),
                itemView.findViewById(R.id.option_1_17),
                itemView.findViewById(R.id.option_1_18),
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
    class TypeViewHolderScenarioTwoScreenFour(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        private val optionSets: List<List<TextView>> = listOf(
            listOf
                (
                itemView.findViewById(R.id.option_1_1),
                itemView.findViewById(R.id.option_1_2),
                itemView.findViewById(R.id.option_1_3),
                itemView.findViewById(R.id.option_1_4),
                itemView.findViewById(R.id.option_1_5),
                itemView.findViewById(R.id.option_1_6),
                itemView.findViewById(R.id.option_1_7)
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
    class TypeViewHolderScenarioTwoScreenFive(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: MakingConnectionDataClass) {


        }
        var saveBtn : AppCompatButton = itemView.findViewById(R.id.yesButton)
        var savedBtn : AppCompatButton = itemView.findViewById(R.id.savedButton)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeViewHolderA -> {
                holder.bind(items[position])
                holder.playPauseButton.setOnClickListener{
                    Toast.makeText(context, "Audio Not Available", Toast.LENGTH_SHORT).show()
                }
            }
            is TypeViewHolderFive ->{
                holder.bind(items[position])
                holder.text1.setOnClickListener{

                    holder.text1.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.text2.setBackgroundResource(R.drawable.default_option_background)
                    holder.text2.setTextColor(Color.parseColor("#424242"))
                    showPositiveDialog(holder.itemView)
                }
                holder.text2.setOnClickListener{


                    holder.text1.setBackgroundResource(R.drawable.default_option_background)
                    holder.text1.setTextColor(Color.parseColor("#424242"))
                    holder.text2.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text2.setTextColor(Color.parseColor("#FFFFFF"))
                    showNegativeDialog(holder.itemView)
                }

                holder.text_2_1.setOnClickListener{

                    holder.text_2_1.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text_2_1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.text_2_2.setBackgroundResource(R.drawable.default_option_background)
                    holder.text_2_2.setTextColor(Color.parseColor("#424242"))
                    showPositiveDialog(holder.itemView)
                }
                holder.text_2_2.setOnClickListener{


                    holder.text_2_1.setBackgroundResource(R.drawable.default_option_background)
                    holder.text_2_1.setTextColor(Color.parseColor("#424242"))
                    holder.text_2_2.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text_2_2.setTextColor(Color.parseColor("#FFFFFF"))
                    showNegativeDialog(holder.itemView)
                }


            }
            is TypeViewHolderSeven ->{
                holder.bind(items[position])
                holder.bind(items[position])
                holder.text1.setOnClickListener{

                    holder.text1.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.text2.setBackgroundResource(R.drawable.default_option_background)
                    holder.text2.setTextColor(Color.parseColor("#424242"))
                    showPositiveDialog(holder.itemView)
                }
                holder.text2.setOnClickListener{

                    holder.text1.setBackgroundResource(R.drawable.default_option_background)
                    holder.text1.setTextColor(Color.parseColor("#424242"))
                    holder.text2.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text2.setTextColor(Color.parseColor("#FFFFFF"))
                    showNegativeDialog(holder.itemView)
                }
                holder.text2_1.setOnClickListener{

                    holder.text2_1.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text2_1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.text2_2.setBackgroundResource(R.drawable.default_option_background)
                    holder.text2_2.setTextColor(Color.parseColor("#424242"))
                    showPositiveDialog(holder.itemView)
                }
                holder.text2_2.setOnClickListener{

                    holder.text2_1.setBackgroundResource(R.drawable.default_option_background)
                    holder.text2_1.setTextColor(Color.parseColor("#424242"))
                    holder.text2_2.setBackgroundResource(R.drawable.card_selected_background)
                    holder.text2_2.setTextColor(Color.parseColor("#FFFFFF"))
                    showNegativeDialog(holder.itemView)
                }
            }

            is TypeViewHolderTenFour->{
                holder.bind(items[position])
                holder.saveBtn.setOnClickListener {
                    holder.saveBtn.visibility = View.GONE;
                    holder.savedBtn.visibility = View.VISIBLE;
                }
            }
            is TypeViewHolderScenarioTwoScreenFive ->{
                holder.bind(items[position])
                holder.saveBtn.setOnClickListener {
                    holder.saveBtn.visibility = View.GONE;
                    holder.savedBtn.visibility = View.VISIBLE;
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].theViewType
    }

    private fun showPositiveDialog(view: View) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.making_connection_dialog_correct, null)
        val okButton = dialogView.findViewById<AppCompatButton>(R.id.okButton)


        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        okButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showNegativeDialog(view: View) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.making_connection_dialog_wrong, null)
        val okButton = dialogView.findViewById<AppCompatButton>(R.id.wrong_okButton)


        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        okButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}