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

package com.calmscient.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.calmscient.R

class CustomDialogFragment(context: Context, private val message: String) : Dialog(context) {
    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.adapter_review_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.tvReviewInfo)
        val closeBtn = view.findViewById<ImageView>(R.id.closeButton)

        // Set title, content, and button click listener
        title.text = "Dialog Title"

        closeBtn.setOnClickListener {
            // Handle button click
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.adapter_review_dialog)

        val title = findViewById<TextView>(R.id.titleTextDialog)
        val tvInfo = findViewById<TextView>(R.id.tvReviewInfo)
        val closeBtn = findViewById<ImageView>(R.id.closeButton)
        title.text = message
        //tvInfo.text = info
        if(message.contains("All or nothing (black and white) thinking")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_one)
        }else if(message.contains("Catastrophizing")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_two)
        }else if(message.contains("Perfectionist thinking")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_three)
        }else if(message.contains("Negative (mental) filtering")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_four)
        }else if(message.contains("Personalization and blame")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_five)
        }else if(message.contains("Jumping to conclusions")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_six)
        }else if(message.contains("Making negative assumptions")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_seven)
        }else if(message.contains("Emotional reasoning (“fear talk”)")){
            tvInfo.text = context.getString(R.string.anxiety_review_think_eight)
        }

        closeBtn.setOnClickListener {
            dismiss()
        }
    }

}
