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
import com.calmscient.di.remote.PostponeWorryDataClass

class AnxietyPostponeAdapter(
    private val items: MutableList<PostponeWorryDataClass>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_TYPE_A = 0
        const val VIEW_TYPE_TYPE_B = 1
        const val VIEW_TYPE_TYPE_C = 2
        const val VIEW_TYPE_TYPE_C_A = 3
        const val VIEW_TYPE_TYPE_D = 4
        const val VIEW_TYPE_TYPE_D_A = 5
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        /*val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
        return MyViewHolder(itemView)*/
        //layout_postpone_worry_quiz1
        return when (viewType) {
            VIEW_TYPE_TYPE_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_header_screen, parent, false)
                TypeViewHolderA(view)
            }

            VIEW_TYPE_TYPE_B -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_worry_quiz1, parent, false)
                TypeAViewHolder(view)
            }

            VIEW_TYPE_TYPE_C -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
                MyViewHolder(view)
            }
            VIEW_TYPE_TYPE_C_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_worry_two, parent, false)
                MyViewHolderPartB(view)
            }

            VIEW_TYPE_TYPE_D -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_comeback_screen, parent, false)
                TypeViewHolderD(view)
            }
            VIEW_TYPE_TYPE_D_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_comeback_2, parent, false)
                TypeViewHolderD2(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder.itemViewType) {
            VIEW_TYPE_TYPE_A -> {
                val viewHolderTypeA = holder as TypeViewHolderA
                viewHolderTypeA.bind(item)
            }

            VIEW_TYPE_TYPE_B -> {
                val viewHolderTypeB = holder as TypeAViewHolder
                viewHolderTypeB.bind(item)
            }

            VIEW_TYPE_TYPE_C -> {
                val viewHolderTypeC = holder as MyViewHolder
                viewHolderTypeC.bind(item)
            }
            VIEW_TYPE_TYPE_C_A -> {
                val viewHolderTypeC_A = holder as MyViewHolderPartB
                viewHolderTypeC_A.bind(item)
            }
            VIEW_TYPE_TYPE_D -> {
                val viewHolderTypeD = holder as TypeViewHolderD
                viewHolderTypeD.bind(item)
            }
            VIEW_TYPE_TYPE_D_A -> {
                val viewHolderTypeD_A = holder as TypeViewHolderD2
                viewHolderTypeD_A.bind(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text0_1)
        val text2: TextView = itemView.findViewById(R.id.text1_1)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageanxiety_1)
        val text3: TextView = itemView.findViewById(R.id.text0_2)
        val text4: TextView = itemView.findViewById(R.id.text6)
        val text7: TextView = itemView.findViewById(R.id.text7)
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList
            /*text1.text = recyclerViewModel.text1
            text2.text = recyclerViewModel.text2*/
        }
    }
    class MyViewHolderPartB(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.text2_1)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageanxiety2_1)
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList

        }
    }

    class TypeAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    class TypeViewHolderA(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }
    class TypeViewHolderD(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }
    class TypeViewHolderD2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: PostponeWorryDataClass) {
            val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Return the view type based on the position or data
        /*return when(items[position]) {
            is AnxietyPostPoneDataClass ->VIEW_TYPE_TYPE_A
            is AnxietyPostPoneDataClass->VIEW_TYPE_TYPE_B
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }*/
        //return if (items.get(position).text1 == (R.string.anxiety_postPoneCard0_text1).toString()) VIEW_TYPE_TYPE_A else VIEW_TYPE_TYPE_B
        return items[position].theViewType
    }

}