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

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.calmscient.Interface.BottomSheetListener
import com.calmscient.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetEditFragment(private val view: View, val position: Int, val description: String) : BottomSheetDialogFragment() {
    lateinit var etEdit: EditText
    lateinit var updateButton: AppCompatButton
    lateinit var closeButton: ImageView
    private var listener: BottomSheetListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.journal_entry_edit_bottomsheet, container, false)
        etEdit = rootView.findViewById(R.id.edit_daily_journel)
        etEdit.setText(description)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButton = view.findViewById(R.id.updateButton)
        closeButton = view.findViewById(R.id.closeEditButton)
        etEdit.imeOptions = EditorInfo.IME_ACTION_DONE
        etEdit.setRawInputType(InputType.TYPE_CLASS_TEXT)
        updateButton.setOnClickListener {
            listener?.onShowEditBottomSheet(view,position,description)
            showCustomDialog(requireContext())
            dismiss()
        }
        closeButton.setOnClickListener {
            dismiss()
        }

    }
    override fun onResume() {
        super.onResume()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    private fun showCustomDialog(context: Context) {
        // Create and configure the custom dialog
        val dialogView = LayoutInflater.from(context).inflate(R.layout.journal_update_dialog, null)
        val dialog = android.app.AlertDialog.Builder(context, R.style.CustomDialog)
            .setView(dialogView)
            .create()

        // Initialize views in the custom dialog layout
        val titleTextView = dialogView.findViewById<TextView>(R.id.dialog_title)
        val iconImageView = dialogView.findViewById<ImageView>(R.id.dialog_icon)
        val messageTextView = dialogView.findViewById<TextView>(R.id.dialog_message)

        // Show the custom dialog
        dialog.show()

        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()
        }, 3000)
    }
}