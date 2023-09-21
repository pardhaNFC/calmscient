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
import android.widget.TextView
import androidx.activity.addCallback
import com.calmscient.R
import com.calmscient.databinding.FragmentEventsTrackerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventsTrackerFragment : Fragment() {
    private  lateinit var binding: FragmentEventsTrackerBinding
    private lateinit var monthText: TextView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(TakingControlFragment())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventsTrackerBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(TakingControlFragment())
        }
        monthText = binding.monthtext

        val myCalendar = Calendar.getInstance()

        val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }
        binding.hangoverToggleButton.labelOn = getString(R.string.yes)
        binding.hangoverToggleButton.labelOff = getString(R.string.no)

        binding.argumentToggleButton.labelOn = getString(R.string.yes)
        binding.argumentToggleButton.labelOff = getString(R.string.no)

        binding.accidentToggleButton.labelOn = getString(R.string.yes)
        binding.accidentToggleButton.labelOff = getString(R.string.no)
//        calendarView.setOnClickListener {
//            DatePickerDialog(
//                requireContext(),
//                datePickerListener,
//                myCalendar.get(Calendar.YEAR),
//                myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }

        // Initialize the monthText with the current date
        val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.UK).format(Date())
        monthText.text = currentDate

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsTrackerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun updateLabel(myCalendar: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.UK)
        val formattedDate = sdf.format(myCalendar.time)
        monthText.text = formattedDate
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null) // This ensures that the previous fragment is added to the back stack
        transaction.commit()
    }
}