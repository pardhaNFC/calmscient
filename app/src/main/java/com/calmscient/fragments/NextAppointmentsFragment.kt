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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.NextAppointmentsAdapter
import com.calmscient.databinding.CalendarDayLayoutBinding
import com.calmscient.databinding.FragmentNextappointmentsBinding
import com.calmscient.utils.getColorCompat
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
data class CardViewItems(
    val dateview1: String,
    val doctor_logo: Int?,
    val DoctorNameTextView: String?,
    val HosptailNameTextView: String?,
    val img_arrow1: Int?,
    var dateview2: String?,
    val appointments_logo1: Int?,
    val AppointmentsNameTextView1: String?,
    val dateview3: String?,
    val appointments_logo2: Int?,
    val AppointmentsNameTextView2: String?,
    val dateview4: String?,
    val appointments_logo3: Int?,
    val AppointmentsNameTextView3: String?,
    val dateview5: String?,
    val appointments_logo4: Int?,
    val AppointmentsNameTextView4: String?)
class NextAppointmentsFragment : Fragment() {
    private lateinit var binding: FragmentNextappointmentsBinding
    private var selectedDate = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd")
    private lateinit var cardViewAdapter: NextAppointmentsAdapter
    private val cardViewItems = mutableListOf<CardViewItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(MedicalRecordsFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextappointmentsBinding.inflate(inflater, container, false)
        binding.mainlayout.setOnClickListener{
            loadFragment(AppointmentdetailsFragment())
        }
        binding.backIcon.setOnClickListener {
            loadFragment(MedicalRecordsFragment())
        }
        binding.plusIcon.setOnClickListener {
            loadFragment(AddAppointmentFragment())
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        class DayViewContainer(view: View) : ViewContainer(view) {
            val bind = CalendarDayLayoutBinding.bind(view)
            lateinit var day: WeekDay

            init {
                view.setOnClickListener {
                    if (selectedDate != day.date) {
                        val oldDate = selectedDate
                        selectedDate = day.date
                        binding.exSevenCalendar.notifyDateChanged(day.date)
                        oldDate?.let { binding.exSevenCalendar.notifyDateChanged(it) }
                    }
                }
            }
            fun bind(day: WeekDay) {
                this.day = day
                bind.exSevenDateText.text = dateFormatter.format(day.date)
                bind.exSevenDayText.text = day.date.dayOfWeek.displayText()
                val colorRes = if (day.date == selectedDate) {
                    R.color.white
                } else {
                    R.color.black
                }
                bind.exSevenDateText.setTextColor(view.context.getColorCompat(colorRes))
                bind.exSevenDayText.setTextColor(view.context.getColorCompat(colorRes))
                val colorResLayout = if (day.date == selectedDate) {
                    R.drawable.calendar_custom_border
                } else {
                    R.color.example_7_calendar
                }
                bind.layoutDate.setBackgroundResource(colorResLayout)
                //bind.exSevenSelectedView.isVisible = day.date == selectedDate
            }
        }
        binding.exSevenCalendar.dayBinder = object : WeekDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: WeekDay) = container.bind(data)
        }
        binding.exSevenCalendar.weekScrollListener = { weekDays ->
            val text = binding.exSevenToolbar.title
            binding.exSevenToolbar.title = getWeekPageTitle(weekDays)
            binding.exSevenToolbar.setTitleTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }
        val currentMonth = YearMonth.now()
        binding.exSevenCalendar.setup(
            currentMonth.minusMonths(24).atStartOfMonth(),
            currentMonth.plusMonths(24).atEndOfMonth(),
            firstDayOfWeekFromLocale(),
        )
        binding.exSevenCalendar.scrollToDate(LocalDate.now())
        binding.recyclerViewAppointments.layoutManager = LinearLayoutManager(requireContext())
        cardViewAdapter = NextAppointmentsAdapter(cardViewItems)
        binding.recyclerViewAppointments.adapter = cardViewAdapter


        // Call this function when the date is selected
        displayCardViewsForSelectedDate()


    }
    private fun displayCardViewsForSelectedDate() {
        cardViewItems.clear()
        cardViewItems.addAll(
            listOf(
                CardViewItems("08/24/2023",
                    R.drawable.ic_doctor_logo,
                    "Dr. Hannah Johnson",
                    "SCD Hospital",
                    R.drawable.ic_next,
                    "08/25/2023",
                    R.drawable.ic_appointments,
                    "No Appointments",
                    "08/26/2023",
                    R.drawable.ic_appointments,
                    "No Appointments",
                    "08/27/2023",
                    R.drawable.ic_appointments,
                    "No Appointments",
                    "08/28/2023",
                    R.drawable.ic_appointments,
                    "No Appointments")
            )
        )
        cardViewAdapter.notifyDataSetChanged()
    }
    fun getWeekPageTitle(week: Week): String {
        val firstDate = week.days.first().date
        val lastDate = week.days.last().date
        return when {
            firstDate.yearMonth == lastDate.yearMonth -> {
                firstDate.yearMonth.displayText()
            }
            firstDate.year == lastDate.year -> {
                "${firstDate.month.displayText(short = false)} - ${lastDate.yearMonth.displayText()}"
            }
            else -> {
                "${firstDate.yearMonth.displayText()} - ${lastDate.yearMonth.displayText()}"
            }
        }
    }
    fun YearMonth.displayText(short: Boolean = false): String {
        return "${this.month.displayText(short = short)} ${this.year}"
    }
    fun Month.displayText(short: Boolean = true): String {
        val style = if (short) TextStyle.SHORT else TextStyle.FULL
        return getDisplayName(style, Locale.ENGLISH)
    }
    fun DayOfWeek.displayText(uppercase: Boolean = false): String {
        return getDisplayName(TextStyle.SHORT, Locale.ENGLISH).let { value ->
            if (uppercase) value.uppercase(Locale.ENGLISH) else value
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null) // This ensures that the previous fragment is added to the back stack
        transaction.commit()
    }
}
