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

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.FragmentMedicationDetailBinding
import com.calmscient.receivers.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class MedicationDetailFragment : Fragment(),
    ScheduleTimeAndAlarmDialogFragment.TimeAndAlarmSaveClickListener {
    private lateinit var binding: FragmentMedicationDetailBinding
    private var isMorningAlarmOn = false
    private var isEveningAlarmOn = false
    private var morningTime: String = ""
    private var morningAlarm: String = ""
    private var eveningTime: String = ""
    private var eveningAlarm: String = ""
    private var selectedSchedule: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(CalendarFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicationDetailBinding.inflate(inflater, container, false)
        binding.alarmToggleButtonMorning.labelOn = getString(R.string.yes)
        binding.alarmToggleButtonMorning.labelOff = getString(R.string.no)
        binding.alarmToggleButtonAfternoon.labelOn = getString(R.string.yes)
        binding.alarmToggleButtonAfternoon.labelOff = getString(R.string.no)

        binding.alarmToggleButtonEvening.labelOn = getString(R.string.yes)
        binding.alarmToggleButtonEvening.labelOff = getString(R.string.no)
        binding.backIcon.setOnClickListener {
            loadFragment(CalendarFragment())
        }

       /* binding.morningCalendar.setOnClickListener {
            showMorningTimeAndAlarmDialog()
        }

        binding.eveningCalendar.setOnClickListener {
            showEveningTimeAndAlarmDialog()
        }*/
        binding.morningAlarmCard.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }
        binding.afternoonAlarmCard.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }
        binding.eveningAlarmCard.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }
        return binding.root;
        // return inflater.inflate(R.layout.fragment_medication_detail, container, false)
    }

    private fun showMorningTimeAndAlarmDialog() {
        selectedSchedule = "Morning"
        val morningDialog = ScheduleTimeAndAlarmDialogFragment.newInstance("Morning", this)
        morningDialog.show(childFragmentManager, ScheduleTimeAndAlarmDialogFragment.TAG)
    }

    private fun showEveningTimeAndAlarmDialog() {
        selectedSchedule = "Evening"
        val eveningDialog = ScheduleTimeAndAlarmDialogFragment.newInstance("Evening", this)
        eveningDialog.show(childFragmentManager, ScheduleTimeAndAlarmDialogFragment.TAG)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSaveClicked(time: String, alarm: String) {
        val currentTime = getCurrentTime()
        val selectedTime = SimpleDateFormat("HH:mm").parse(alarm)

        if (currentTime.before(selectedTime)) {
            // The selected time is in the future, schedule the alarm for today
            when (selectedSchedule) {
                "Morning" -> {
                    morningTime = time
                    morningAlarm = alarm
                    binding.morningTimeView.text = morningTime
                    binding.morningAlarmTimeView.text = morningAlarm
                    if (isMorningAlarmOn) {
                        scheduleAlarm(morningAlarm, "Morning")
                    } else {
                        cancelAlarm("Morning")
                    }
                }

                "Evening" -> {
                    eveningTime = time
                    eveningAlarm = alarm
                    binding.eveningTimeView.text = eveningTime
                    binding.eveningAlarmTimeView.text = eveningAlarm
                    if (isEveningAlarmOn) {
                        scheduleAlarm(eveningAlarm, "Evening")
                    } else {
                        cancelAlarm("Evening")
                    }
                }
                // ... Add other schedule types if needed
            }
        } else {
            // The selected time is in the past, schedule the alarm for the next day
            val calendar = Calendar.getInstance()
            calendar.time = selectedTime
            calendar.add(Calendar.DAY_OF_YEAR, 1)

            when (selectedSchedule) {
                "Morning" -> {
                    morningTime = time
                    morningAlarm = SimpleDateFormat("HH:mm").format(calendar.time)
                    binding.morningTimeView.text = morningTime
                    binding.morningAlarmTimeView.text = morningAlarm
                    if (isMorningAlarmOn) {
                        scheduleAlarm(morningAlarm, "Morning")
                    } else {
                        cancelAlarm("Morning")
                    }
                }

                "Evening" -> {
                    eveningTime = time
                    eveningAlarm = SimpleDateFormat("HH:mm").format(calendar.time)
                    binding.eveningTimeView.text = eveningTime
                    binding.eveningAlarmTimeView.text = eveningAlarm
                    if (isEveningAlarmOn) {
                        scheduleAlarm(eveningAlarm, "Evening")
                    } else {
                        cancelAlarm("Evening")
                    }
                }
            }
        }
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }

    private fun scheduleAlarm(time: String, scheduleType: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("SCHEDULE_TYPE", scheduleType)

        val requestCode =
            if (scheduleType == "Morning") 0 else 1 // Use different request codes for Morning and Evening

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val timeParts = time.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        // First, cancel any existing alarms for the same schedule type
        cancelAlarm(scheduleType)
        // Schedule the new alarm
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }


    private fun cancelAlarm(scheduleType: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("SCHEDULE_TYPE", scheduleType)

        val requestCode =
            if (scheduleType == "Morning") 0 else 1 // Use the same request codes as used in scheduling

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Cancel the scheduled alarm
        alarmManager.cancel(pendingIntent)
    }
}
