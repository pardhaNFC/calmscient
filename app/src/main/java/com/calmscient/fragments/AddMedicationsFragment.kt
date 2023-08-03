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
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.FragmentAddMedicationsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


interface OnKeyboardVisibilityChangeListener {
    fun onKeyboardVisibilityChanged(isVisible: Boolean)
}

class AddMedicationsFragment : Fragment(),OnKeyboardVisibilityChangeListener {

    private lateinit var binding: FragmentAddMedicationsBinding
    private  var isKeyboardVisible = false

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

        binding.morningCalendar.setOnClickListener {
            showMorningTimeAndAlarmDialog()
        }

        binding.eveningCalendar.setOnClickListener {
            showEveningTimeAndAlarmDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)

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
            false // Return false to allow other touch events to be handled
        }


        // Listening to focus change of the EditText
        setBottomNavVisibilityOnFocusChange(binding.userName, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.provider, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.dosage, bottomNavView)
        setBottomNavVisibilityOnFocusChange(binding.direction, bottomNavView)
    }

    override fun onKeyboardVisibilityChanged(isVisible: Boolean) {
        isKeyboardVisible = isVisible
        if (!isKeyboardVisible) {
            // Keyboard is hidden, show the bottom navigation menu
            val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavView.visibility = View.VISIBLE
        }
    }

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
        val morningDialog = ScheduleTimeAndAlarmDialogFragment.newInstance("Morning")
        morningDialog.show(childFragmentManager, ScheduleTimeAndAlarmDialogFragment.TAG)
    }
    private fun showEveningTimeAndAlarmDialog() {
        val eveningDialog = ScheduleTimeAndAlarmDialogFragment.newInstance("Evening")
        eveningDialog.show(childFragmentManager, ScheduleTimeAndAlarmDialogFragment.TAG)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
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
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.parentLayout.windowToken, 0)

        // Show the bottom navigation menu
        bottomNavView.visibility = View.VISIBLE
    }


    companion object {
        fun newInstance() = AddMedicationsFragment()
    }
}