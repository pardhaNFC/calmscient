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
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.data.remote.ReviewItemDataClass
import com.calmscient.fragments.CustomDialogFragment

class RestructureReviewAdapter(val context: Context, private val items: List<ReviewItemDataClass>) :
    RecyclerView.Adapter<RestructureReviewAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestructureReviewAdapter.TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_review_layout, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RestructureReviewAdapter.TaskViewHolder, position: Int) {
        val item = items[position]
        holder.btnItem.text = item.text
        holder.llItem.setOnClickListener {
            //Toast.makeText(context, "Clicked" + item.text, Toast.LENGTH_SHORT).show()
            //showCustomDialog(context,item.text)
            showInformationDialog(context,item.text)
        }
    }
    private fun showInformationDialog(context:Context,message: String) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.adapter_review_dialog, null)
        val title = dialogView.findViewById<TextView>(R.id.titleTextDialog)
        val tvInfo = dialogView.findViewById<TextView>(R.id.tvReviewInfo)
        val closeBtn = dialogView.findViewById<ImageView>(R.id.closeButton)

        title.text = message
        if (message.contains("All or nothing (black and white) thinking")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_one)
        } else if (message.contains("Catastrophizing")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_two)
        } else if (message.contains("Perfectionist thinking")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_three)
        } else if (message.contains("Negative (mental) filtering")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_four)
        } else if (message.contains("Personalization and blame")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_five)
        } else if (message.contains("Jumping to conclusions")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_six)
        } else if (message.contains("Making negative assumptions")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_seven)
        } else if (message.contains("Emotional reasoning (“fear talk”)")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_eight)
        }
        val dialogBuilder = context?.let {
            AlertDialog.Builder(it, R.style.CustomDialog)
                .setView(dialogView)
        }

        val dialog = dialogBuilder?.create()
        dialog?.show()

        // Handle the close button click
        closeBtn.setOnClickListener {
            dialog?.dismiss()
        }
    }
    inner class TaskViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val btnItem = itemView.findViewById<AppCompatTextView>(R.id.btn_reviewItem)
        val llItem = itemView.findViewById<LinearLayout>(R.id.ll_reviewItem)
    }

    private fun showCustomDialog(context:Context,message: String) {
        val customDialog = CustomDialogFragment(context, message)
        customDialog.show()
    }
}