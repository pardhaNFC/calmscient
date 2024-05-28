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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.di.remote.AnxietyAndAlcoholSubstanceDataClass

class AnxietyAndAlcoholAdapter(
    private val context: Context,
    val items: MutableList<AnxietyAndAlcoholSubstanceDataClass>
) :
    RecyclerView.Adapter<AnxietyAndAlcoholAdapter.AnxietyAndAlcoholViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnxietyAndAlcoholViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.anxity_and_alcohol_item_card, parent, false)
        return AnxietyAndAlcoholViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnxietyAndAlcoholViewHolder, position: Int) {
        val item = items[position]

        item.bulbImage?.let { holder.bulb.setImageResource(it) }

        item.image?.let { holder.image.setImageResource(it) }

        if (item.textDesc1.isNullOrBlank()) {
            holder.text1.visibility = View.GONE
        } else {
            holder.text1.text = item.textDesc1

            //holder.text1.text = spannedText
            holder.text1.visibility = View.VISIBLE
        }

        if (item.textDesc2.isNullOrBlank()) {
            holder.text2.visibility = View.GONE
        } else {
            holder.text2.text = item.textDesc2
            holder.text2.visibility = View.VISIBLE
        }

       if(position == 0)
       {
          holder.trackingButton.visibility = View.GONE;
       }



        holder.bulb.setOnClickListener {
          showInformationDialog();
        }

    }

    private fun showInformationDialog() {
        val dialogView =  LayoutInflater.from(context).inflate(R.layout.recognize_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)


        titleTextView.text = context.getString(R.string.did_you_know)
        infoTextView.text = context.getString(R.string.anxiety_and_alcohol_card_1_dialog_text)
        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()


        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun getItemCount(): Int = items.size


    inner class AnxietyAndAlcoholViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bulb: ImageView = itemView.findViewById(R.id.bulb_icon)
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val text1: TextView = itemView.findViewById(R.id.tv_anxiety_and_alcohol_desc_1)
        val text2: TextView = itemView.findViewById(R.id.tv_anxiety_and_alcohol_desc_2)
        val trackingButton : AppCompatButton = itemView.findViewById(R.id.alcohol_substance_tracking_button)

    }


}
