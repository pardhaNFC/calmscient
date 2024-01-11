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

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.di.remote.AnxietyRestructureDataClass


class AdapterRestructureBinding(val context: Context,
                                private val items: MutableList<AnxietyRestructureDataClass>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_TYPE_A = 0
    }

    val VIEW_TYPE_TYPE_A: Int = 0
    val VIEW_TYPE_TYPE_B: Int = 1
    val VIEW_TYPE_TYPE_C: Int = 2


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
            /*VIEW_TYPE_TYPE_C -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
                MyViewHolder(view)
            }

            VIEW_TYPE_TYPE_C_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
                MyViewHolder(view)
            }

            VIEW_TYPE_TYPE_D -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
                MyViewHolder(view)
            }

            VIEW_TYPE_TYPE_D_A -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_postpone_quiz_adapter, parent, false)
                MyViewHolder(view)
            }
*/
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
        val checkboxText1: TextView = itemView.findViewById(com.calmscient.R.id.custom_checkbox_1)
    }
    class TypeViewHolderScreenThree(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemsList: AnxietyRestructureDataClass) {
            //val recyclerViewModel = itemsList.text1
            //message.text = recyclerViewModel.textData
        }
        val checkboxText1: TextView = itemView.findViewById(com.calmscient.R.id.custom_checkbox_1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeViewHolderA -> {
                holder.bind(items[position])
            }
            is TypeViewHolderScreenTwo -> {
                //holder.bind(items[position])
               /* if (holder.includedLayout1.visibility == View.VISIBLE) {
                    holder.checkboxText1.text = context.getString(com.calmscient.R.string.anxiety_restructure_screen2_checkOne)
                } else if (holder.includedLayout2.visibility == View.VISIBLE) {
                    //holder.checkboxText1.setText("Job security or performance")
                    holder.checkboxText1.text = context.getString(com.calmscient.R.string.anxiety_restructure_screen2_checkTwo)
                }*/

            }

            /* is MyViewHolder -> {
                 holder.bind(items[position])
             }*/
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].theViewType
    }
}