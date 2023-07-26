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

import android.view.ViewGroup
import androidx.annotation.AnimRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.databinding.CalendarViewOptionsItemViewBinding
import com.calmscient.fragments.BaseFragment
import com.calmscient.fragments.Example7Fragment
import com.calmscient.utils.layoutInflater


data class ExampleItem(
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    val animation: Animation,
    val createView: () -> BaseFragment,
)

data class Animation(
    @AnimRes val enter: Int,
    @AnimRes val exit: Int,
    @AnimRes val popEnter: Int,
    @AnimRes val popExit: Int,
)

val vertical = Animation(
    enter = R.anim.slide_in_up,
    exit = R.anim.fade_out,
    popEnter = R.anim.fade_in,
    popExit = R.anim.slide_out_down,
)

val horizontal = Animation(
    enter = R.anim.slide_in_right,
    exit = R.anim.slide_out_left,
    popEnter = R.anim.slide_in_left,
    popExit = R.anim.slide_out_right,
)

class CalendarViewOptionsAdapter (val onClick: (ExampleItem) -> Unit) :
    RecyclerView.Adapter<CalendarViewOptionsAdapter.HomeOptionsViewHolder>() {
    val examples = listOf(
        ExampleItem(
            R.string.welcome_message,
            R.string.welcome_message,
            horizontal,
        ) { Example7Fragment() },

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOptionsViewHolder {
        return HomeOptionsViewHolder(
            CalendarViewOptionsItemViewBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(viewHolder: HomeOptionsViewHolder, position: Int) {
        viewHolder.bind(examples[position])
    }

    override fun getItemCount(): Int = examples.size
    inner class HomeOptionsViewHolder(private val binding: CalendarViewOptionsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(examples[bindingAdapterPosition])
            }
        }

        fun bind(item: ExampleItem) {
            val context = itemView.context
            binding.itemOptionTitle.text = context.getString(item.titleRes)
            binding.itemOptionSubtitle.text = context.getString(item.subtitleRes)
        }
    }
}