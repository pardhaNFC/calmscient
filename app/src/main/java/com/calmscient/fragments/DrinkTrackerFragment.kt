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
    private  lateinit var binding: FragmentDrinkTrackerBinding
    private lateinit var calenderView: ImageView
    private lateinit var monthText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(TakingControlFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkTrackerBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(TakingControlFragment())
        }
        binding.saveButton.setOnClickListener {
            loadFragment(DrinkTrackerFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calenderView = view.findViewById(R.id.calenderview)
        monthText = view.findViewById(R.id.monthtext)

//create a date string.
        val date_n: String = SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date()) //get hold of textview.
        monthText.text = date_n

        val myCalender = Calendar.getInstance()

        val datePickerListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalender)
        }

        calenderView.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePickerListener,
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateLabel(myCalender: Calendar) {
        val monthNames = arrayOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )

        val currentTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.UK)
        val formattedDate = sdf.format(myCalender.time)


        monthText.text = " $formattedDate"
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null) // This ensures that the previous fragment is added to the back stack
        transaction.commit()
    }
}
