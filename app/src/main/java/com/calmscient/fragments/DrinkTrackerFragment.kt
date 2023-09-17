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
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import com.calmscient.R
import com.calmscient.databinding.FragmentDrinkTrackerBinding
import com.calmscient.databinding.LayoutSummaryTakingControlBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
class DrinkTrackerFragment : Fragment() {
    private lateinit var binding: FragmentDrinkTrackerBinding
    private lateinit var calendarView: ImageView
    private lateinit var monthText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkTrackerBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(TakingControlFragment())
        }
        calendarView = binding.calenderview
        monthText = binding.monthtext

        val myCalendar = Calendar.getInstance()

        val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        calendarView.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePickerListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Initialize the monthText with the current date
        val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.UK).format(Date())
        monthText.text = currentDate

        return binding.root
    }

    private fun updateLabel(myCalendar: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.UK)
        val formattedDate = sdf.format(myCalendar.time)
        monthText.text = formattedDate
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
