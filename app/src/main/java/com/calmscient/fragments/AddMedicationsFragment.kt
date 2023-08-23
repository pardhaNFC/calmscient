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
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.FragmentAddMedicationsBinding
import com.calmscient.receivers.AlarmReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class AddMedicationsFragment : Fragment(),
    ScheduleTimeAndAlarmDialogFragment.TimeAndAlarmSaveClickListener {

    private lateinit var binding: FragmentAddMedicationsBinding
    private var isKeyboardVisible = false
    private var isMorningAlarmOn = false
    private var isEveningAlarmOn = false
    private var morningTime: String = ""
    private var morningAlarm: String = ""
    private var eveningTime: String = ""
    private var eveningAlarm: String = ""
    private var selectedSchedule: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(CalendarFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMedicationsBinding.inflate(inflater, container, false)
        //going back to Medications Fragment
        binding.addMedicationsDetailsBackIcon.setOnClickListener()
        {
            //loadFragment(MedicationsFragment())
            loadFragment(CalendarFragment())
        }

        //For with meal
        binding.idSwitch.labelOn = "Yes"
        binding.idSwitch.labelOff = "No"

        //For Morning Alarm
        binding.alarmToggleButtonMorning.labelOn = "Yes"
        binding.alarmToggleButtonMorning.labelOff = "No"

        //For Evening Alarm
        binding.alarmToggleButtonEvening.labelOn = "Yes"
        binding.alarmToggleButtonEvening.labelOff = "No"
        //afternoon alarm
        binding.alarmToggleButtonAfternoon.labelOn = "Yes"
        binding.alarmToggleButtonAfternoon.labelOff = "No"


        /*binding.morningCalendar.setOnClickListener {
            showMorningTimeAndAlarmDialog()
        }

        binding.eveningCalendar.setOnClickListener {
            showEveningTimeAndAlarmDialog()
        }*/
        binding.btnAddCancel.setOnClickListener {
            loadFragment(CalendarFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible = keypadHeight > screenHeight * 0.15
            updateBottomNavVisibility(bottomNavView)
        }

        // Set a touch listener to the parent layout
        binding.parentLayout.setOnTouchListener { _, _ ->
            clearFocusAndHideKeyboard(bottomNavView)
            false
        }


        // Listening to focus change of the EditText
        setBottomNavVisibilityOnFocusChange(binding.userName, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.provider, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.dosage, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.direction, bottomNavView)
        binding.alarmToggleButtonMorning.setOnToggledListener { _, isOn ->
            isMorningAlarmOn = isOn
            if (isOn) {
                // Toggle is on, schedule the morning alarm if time is selected
                if (!morningTime.isNullOrEmpty()) {
                    scheduleAlarm(morningTime, "Morning")
                }
            } else {
                // Toggle is off, cancel the morning alarm
                cancelAlarm("Morning")
            }
        }
    }

    /*override fun onKeyboardVisibilityChanged(isVisible: Boolean) {
        isKeyboardVisible = isVisible
        if (!isKeyboardVisible) {
            // Keyboard is hidden, show the bottom navigation menu
            val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavView.visibility = View.VISIBLE
        }
    }*/

    private fun updateBottomNavVisibility(bottomNavView: BottomNavigationView) {
        if (!isKeyboardVisible && !isAnyEditTextFocused()) {
            bottomNavView.visibility = View.VISIBLE
        } else {
            bottomNavView.visibility = View.GONE
        }
    }

    private fun isAnyEditTextFocused(): Boolean {
        return binding.userName.hasFocus() || binding.provider.hasFocus() ||
                binding.dosage.hasFocus() || binding.direction.hasFocus()
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

    fun setBottomNavVisibilityOnFocusChange(view: View, bottomNavView: BottomNavigationView) {
        view.setOnFocusChangeListener { _, hasFocus ->
            bottomNavView.visibility = if (hasFocus) View.GONE else View.VISIBLE
        }
    }

    private fun clearFocusAndHideKeyboard(bottomNavView: BottomNavigationView) {
        // Clear focus from all EditText views
        binding.userName.clearFocus()
        binding.provider.clearFocus()
        binding.dosage.clearFocus()
        binding.direction.clearFocus()

        // Hide the keyboard
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.parentLayout.windowToken, 0)

        // Show the bottom navigation menu
        bottomNavView.visibility = View.VISIBLE
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
            PendingIntent.FLAG_IMMUTABLE // Use FLAG_UPDATE_CURRENT to update existing PendingIntent
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

    private fun getAMPMIndicator(time: String): String {
        val parts = time.split(":")
        val hour = parts[0].toInt()
        return if (hour < 12) "AM" else "PM"
    }

    private fun convertTo12HourFormat(time: String): String {
        val parts = time.split(":")
        val hour = parts[0].toInt()
        val minute = parts[1]

        val amPm = if (hour < 12) "AM" else "PM"
        val hour12 = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour

        return String.format("%02d:%s %s", hour12, minute, amPm)
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
                    binding.morningTextView.text = morningTime
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
                    binding.eveningAlarmTextView.text = eveningAlarm
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
                    isMorningAlarmOn = true
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
                    isEveningAlarmOn = true
                    binding.eveningAlarmTimeView.text = eveningTime
                    binding.eveningAlarmTimeView.text = eveningAlarm
                    if (isEveningAlarmOn) {
                        scheduleAlarm(eveningAlarm, "Evening")
                        isEveningAlarmOn = false
                    } else {
                        cancelAlarm("Evening")
                    }
                }
                // ... Add other schedule types if needed
            }
        }
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }
}