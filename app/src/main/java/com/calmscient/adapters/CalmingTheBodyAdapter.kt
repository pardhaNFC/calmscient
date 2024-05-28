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
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.AnxietyCalmingDataClass


class CalmingTheBodyAdapter(
    val context: Context,
    private val items: MutableList<AnxietyCalmingDataClass>
) :
    RecyclerView.Adapter<CalmingTheBodyAdapter.AnxietyCalmingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalmingTheBodyAdapter.AnxietyCalmingViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_calmingbody, parent, false)
        return AnxietyCalmingViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CalmingTheBodyAdapter.AnxietyCalmingViewHolder,
        position: Int
    ) {
        val item = items[position]
        // Check and set text1
        if (item.text1.isNullOrBlank()) {
            holder.text1.visibility = View.GONE
        } else {
            holder.text1.text = item.text1
            holder.text1.visibility = View.VISIBLE
        }
        // Check and set text2
        if (item.text2.isNullOrBlank()) {
            holder.text2.visibility = View.GONE
        } else {
            holder.text2.text = item.text2
            holder.text2.visibility = View.VISIBLE
        }
        if (item.text2_1.isNullOrBlank()) {
            holder.text2_1.visibility = View.GONE
        } else {
            holder.text2_1.text = item.text2_1
            holder.text2_1.visibility = View.VISIBLE
        }
        item.imageanxiety?.let { holder.imageanxiety.setImageResource(it) }
        // Check and set text3
        if (item.text3.isNullOrBlank()) {
            holder.text3.visibility = View.GONE
        } else {
            holder.text3.text = item.text3
            holder.text3.visibility = View.VISIBLE
        }
        if (item.text3_1.isNullOrBlank()) {
            holder.text3_1.visibility = View.GONE
        } else {
            holder.text3_1.text = item.text3_1
            holder.text3_1.visibility = View.VISIBLE
        }
        holder.text2_1.setOnClickListener {
            //showInformationDialog()
        }
        holder.text2_1.setOnClickListener { v ->
            showInformationDialog(v, context.getString(R.string.anxiety_calming_title_two),
                context.getString(R.string.anxiety_calming_card_dialog_one),context.getString(R.string.anxiety_calming_card_dialog_one2))
        }

        holder.text3_1.setOnClickListener { v ->
            showInformationDialog(v, context.getString(R.string.anxiety_calming_title_two),
                context.getString(R.string.anxiety_calming_card_dialog_two1),context.getString(R.string.anxiety_calming_card_dialog_two2))
        }
        if(position==(items.size-1)){
            // here goes some code
            holder.scrollViewCardLast.visibility = View.VISIBLE
            holder.scrollViewCardFirst.visibility = View.GONE
        }

    }

    private fun showInformationDialog(view: View, title:String,categoryText: String,categoryText1: String) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_calming_body, null)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeCalmingButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleCalmingTextView)
        val cardTextView = dialogView.findViewById<TextView>(R.id.tvInfo)
        val cardTextView1 = dialogView.findViewById<TextView>(R.id.tvInfo1)
        val newRightDrawableIce: Drawable? = context.resources.getDrawable(R.drawable.ic_iceberg)
        val newRightDrawableVal: Drawable? = context.resources.getDrawable(R.drawable.ic_volcano)

        titleTextView.text = title
        cardTextView.text = categoryText
        cardTextView1.text = categoryText1
        if(categoryText == context.getString(R.string.anxiety_calming_card_dialog_one)) {
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(
                titleTextView.compoundDrawables[0], // left
                titleTextView.compoundDrawables[1], // top
                newRightDrawableIce, // right
                titleTextView.compoundDrawables[3]  // bottom
            )
        }else{
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(
                titleTextView.compoundDrawables[0], // left
                titleTextView.compoundDrawables[1], // top
                newRightDrawableVal, // right
                titleTextView.compoundDrawables[3]  // bottom
            )
        }


        // Set the new drawable with the existing drawables for left, top, and bottom

        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class AnxietyCalmingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.textCalmTitle)
        val text2: TextView = itemView.findViewById(R.id.textCalmPara1)
        val imageanxiety: ImageView = itemView.findViewById(R.id.imageAnxietyCalming1)
        val text3: TextView = itemView.findViewById(R.id.textCalmPara2)
        val text2_1: TextView = itemView.findViewById(R.id.textinfo1)
        val text3_1: TextView = itemView.findViewById(R.id.textinfo2)
        val scrollViewCardLast: ScrollView = itemView.findViewById(R.id.scroll_calming_last)
        val scrollViewCardFirst: ScrollView = itemView.findViewById(R.id.scrollCardsView)
        /*val text4: TextView = itemView.findViewById(R.id.text4)
        val text5: TextView = itemView.findViewById(R.id.text5)
        val text6: TextView = itemView.findViewById(R.id.text6)
        val text7: TextView = itemView.findViewById(R.id.text7)*/
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}