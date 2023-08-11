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

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.appcompat.widget.AppCompatButton
import com.calmscient.R

class ResultsinfoPopupFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity(), R.style.CustomDialog)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_resultsinfo_popup, null)
        builder.setView(view)

        val btnCancel = view.findViewById<AppCompatButton>(R.id.btncancel)
        btnCancel.setOnClickListener {
            dismiss()
        }

        val btnOk = view.findViewById<AppCompatButton>(R.id.okbutton)
        btnOk.setOnClickListener {
            //requireActivity().finish()
            dismiss()
        }
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        Log.d("ResultsInfoPopupFragment", "Dialog fragment onStart")
    }
}