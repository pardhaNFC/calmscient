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

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.AppComponentFactory
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.calmscient.R
import com.calmscient.databinding.FragmentScheduleTimeAndAlarmDialogBinding

class ScheduleTimeAndAlarmDialogFragment : DialogFragment() {

    interface TimeAndAlarmSaveClickListener {
        fun onSaveClicked(time: String, alarm: String)
    }

    private lateinit var binding: FragmentScheduleTimeAndAlarmDialogBinding
    private var timeAndAlarmSaveClickListener: TimeAndAlarmSaveClickListener? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_schedule_time_and_alarm_dialog, null)


        val timeType = arguments?.getString(ARG_TIME_TYPE)

        // Set the text based on the timeType argument
        val titleText = view.findViewById<TextView>(R.id.titleText)
        titleText.text = timeType
        // Implement your logic here to set the alarm and time using the views in the view variable

        builder.setView(view)
        /* .setPositiveButton("Save") { _, _ ->
             // Save the selected alarm and time here
         }
         .setNegativeButton("Cancel") { dialog, _ ->
             dialog.cancel()
         }*/
        val timePicker = view.findViewById<TimePicker>(R.id.alarmTimePicker)
        timePicker.setIs24HourView(false)
        val (hours, minutes) = getTimeFromTimePicker(timePicker)
        // Set the size of the dialog
        val dialog = builder.create()
        val layoutParams = dialog.window?.attributes
        layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window?.attributes = layoutParams
        val saveButton = view.findViewById<AppCompatButton>(R.id.btnSave)
        saveButton.setOnClickListener {
            // Save the selected time here
            val timePicker = view.findViewById<TimePicker>(R.id.timeTimePicker)
            val (timeHours, timeMinutes) = getTimeFromTimePicker(timePicker)
            val selectedTime = String.format("%02d:%02d", timeHours, timeMinutes)

            val alarmPicker = view.findViewById<TimePicker>(R.id.alarmTimePicker)
            val (alarmHours, alarmMinutes) = getTimeFromTimePicker(alarmPicker)
            val selectedAlarmTime = String.format("%02d:%02d", alarmHours, alarmMinutes)
            // Pass the selected time to the listener
            timeAndAlarmSaveClickListener?.onSaveClicked(selectedTime, selectedAlarmTime)
            dismiss()
        }
        var cancelButton = view.findViewById<AppCompatButton>(R.id.btnCancel)
        cancelButton.setOnClickListener {
            dismiss()
        }
        return builder.create()
    }

    private fun getTimeFromTimePicker(timePicker: TimePicker): Pair<Int, Int> {
        val hours = timePicker.hour
        val minutes = timePicker.minute
        return Pair(hours, minutes)
    }

    companion object {
        const val TAG = "ScheduleTimeAndAlarmDialogFragment"
        const val ARG_TIME_TYPE = "time_type"

        /*fun newInstance(timeType: String): ScheduleTimeAndAlarmDialogFragment {
            val fragment = ScheduleTimeAndAlarmDialogFragment()
            val args = Bundle()
            args.putString(ARG_TIME_TYPE, timeType)
            fragment.arguments = args
            return fragment
        }*/

        fun newInstance(timeType: String, listener: TimeAndAlarmSaveClickListener): ScheduleTimeAndAlarmDialogFragment {
            val fragment = ScheduleTimeAndAlarmDialogFragment()
            val args = Bundle()
            args.putString(ARG_TIME_TYPE, timeType)
            fragment.arguments = args
            fragment.timeAndAlarmSaveClickListener = listener
            return fragment
        }
    }
}