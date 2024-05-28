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

import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import com.calmscient.R

class OneDigitTextWatcher(private val editText: EditText, private val nextEditText: EditText?) : TextWatcher, View.OnKeyListener {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        // Change the border color of the current EditText when text is entered
        s?.let {
            if (it.isNotEmpty()) {
                // Set the border color when a number is entered
                editText.background.setColorFilter(
                    editText.context.resources.getColor(R.color.btn_gradient_color),
                    PorterDuff.Mode.SRC_ATOP
                )

                // Move focus to the next EditText
                nextEditText?.requestFocus()
            }
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL ) {
            // If the Back key is pressed and the EditText is empty, move focus to the previous EditText
            val previousEditText = editText.focusSearch(View.FOCUS_LEFT) as? EditText
            previousEditText?.requestFocus()

            // Reset the border color of the current EditText
            editText.background.clearColorFilter()

            return true
        }
        return false
    }
}

