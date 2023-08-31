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

package com.calmscient.utils

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.calmscient.R

class CustomProgressDialog {
    var context: Context
    lateinit var dialog: Dialog
    lateinit var loading_title: TextView
    lateinit var cardview: CardView
    lateinit var progress: ProgressBar

    constructor(context: Context) {
        this.context = context
    }

    fun show(title:String){

        dialog = Dialog(context)

        dialog.setContentView(R.layout.layout_custom_progress)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loading_title = dialog.findViewById<TextView>(R.id.cp_title)
        cardview = dialog.findViewById(R.id.cp_cardview)
        progress = dialog.findViewById(R.id.cp_pbar)

        loading_title.text = title
        // Card Color
        cardview.setCardBackgroundColor(Color.parseColor("#70000000"))
        setColorFilter(progress.indeterminateDrawable, ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null))


        // Text Color
        loading_title.setTextColor(Color.WHITE)
        dialog.setCancelable(false)
        dialog.create()
        dialog.show()
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun dialogDismiss(){
        dialog.dismiss()
    }

}