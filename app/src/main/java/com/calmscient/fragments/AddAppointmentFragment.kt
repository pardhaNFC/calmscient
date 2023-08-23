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
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.NextAppointmentsAdapter
import com.calmscient.databinding.CalendarDayLayoutBinding
import com.calmscient.databinding.FragmentAddAppointmentBinding
import com.calmscient.databinding.FragmentAppointmentdetailsBinding
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

class AddAppointmentFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddAppointmentBinding
    private var currentClickedId: Int = R.id.mark09
    private var isFirstTime = true
    private var selectedDate = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(NextAppointmentsFragment())
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAppointmentBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(NextAppointmentsFragment())
        }
        binding.cancelButton.setOnClickListener {
            loadFragment(NextAppointmentsFragment())
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
                    R.color.white
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

        val nine = binding.mark09
        val ten = binding.mark10
        val eleven = binding.mark11
        var twelve = binding.mark12
        var thirteen = binding.mark13
        val fourteen = binding.mark14
        val fifteen = binding.mark15
        val sixteen = binding.mark16
        var seventten = binding.mark17
        var eighteen = binding.mark18
        val ninteen = binding.mark19
        var twenty = binding.mark20
        var twentyone = binding.mark21


        nine.setOnClickListener(this)
        ten.setOnClickListener(this)
        eleven.setOnClickListener(this)
        twelve.setOnClickListener(this)
        thirteen.setOnClickListener(this)
        fourteen.setOnClickListener(this)
        fifteen.setOnClickListener(this)
        sixteen.setOnClickListener(this)
        seventten.setOnClickListener(this)
        eighteen.setOnClickListener(this)
        ninteen.setOnClickListener(this)
        twenty.setOnClickListener(this)
        twentyone.setOnClickListener(this)


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

    override fun onResume() {
        super.onResume()
        if (isFirstTime) {
            // Set the background of the English layout to the clicked state
            binding.mark09.setTextColor(requireContext().getColorCompat(R.color.grey_light))
            binding.mark10.setTextColor(requireContext().getColorCompat(R.color.grey_light))
            binding.mark11.setTextColor(requireContext().getColorCompat(R.color.grey_light))
            isFirstTime = false
        }
    }


    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.mark09 || id == R.id.mark10 || id == R.id.mark11 || id == R.id.mark12 ||
            id == R.id.mark13 || id == R.id.mark14 || id == R.id.mark15 || id == R.id.mark16 ||
            id == R.id.mark17 || id == R.id.mark18 || id == R.id.mark19 || id == R.id.mark20 ||
            id == R.id.mark21
        ) {
            updateBackground(currentClickedId, id)

            binding.mark09.setTextColor(
                if (id == R.id.mark09) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark10.setTextColor(
                if (id == R.id.mark10) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark11.setTextColor(
                if (id == R.id.mark11) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )
            binding.mark12.setTextColor(
                if (id == R.id.mark12) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark13.setTextColor(
                if (id == R.id.mark13) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark14.setTextColor(
                if (id == R.id.mark14) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )
            binding.mark15.setTextColor(
                if (id == R.id.mark15) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark16.setTextColor(
                if (id == R.id.mark16) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark17.setTextColor(
                if (id == R.id.mark17) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )
            binding.mark18.setTextColor(
                if (id == R.id.mark18) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )
            binding.mark19.setTextColor(
                if (id == R.id.mark19) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark20.setTextColor(
                if (id == R.id.mark20) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )

            binding.mark21.setTextColor(
                if (id == R.id.mark21) requireContext().getColorCompat(R.color.white)
                else requireContext().getColorCompat(R.color.grey_light)
            )
        }
    }


    private fun updateBackground(previousId: Int, currentId: Int) {
        val previousLayout = when (previousId) {
            R.id.mark09 -> binding.mark09
            R.id.mark10 -> binding.mark10
            R.id.mark11 -> binding.mark11
            R.id.mark12 -> binding.mark12
            R.id.mark13 -> binding.mark13
            R.id.mark14 -> binding.mark14
            R.id.mark15 -> binding.mark15
            R.id.mark16 -> binding.mark16
            R.id.mark17 -> binding.mark17
            R.id.mark18 -> binding.mark18
            R.id.mark19 -> binding.mark19
            R.id.mark20 -> binding.mark20
            R.id.mark21 -> binding.mark21

            else -> return
        }
        previousLayout.setBackgroundResource(R.drawable.circlenormal)
        previousLayout.setTextColor(requireContext().getColorCompat(R.color.grey_light)) // Set text color to grey
        currentClickedId = currentId

        val currentLayout = when (currentId) {
            R.id.mark09 -> binding.mark09
            R.id.mark10 -> binding.mark10
            R.id.mark11 -> binding.mark11
            R.id.mark12 -> binding.mark12
            R.id.mark13 -> binding.mark13
            R.id.mark14 -> binding.mark14
            R.id.mark15 -> binding.mark15
            R.id.mark16 -> binding.mark16
            R.id.mark17 -> binding.mark17
            R.id.mark18 -> binding.mark18
            R.id.mark19 -> binding.mark19
            R.id.mark20 -> binding.mark20
            R.id.mark21 -> binding.mark21

            else -> return
        }
        currentLayout.setBackgroundResource(R.drawable.roundcircle_cliked)
        // Set text color to white

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}