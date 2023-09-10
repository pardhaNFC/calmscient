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
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.Interface.CellClickListener
import com.calmscient.R
import com.calmscient.adapters.JournalEntryAdapter
import com.calmscient.data.remote.JournalEntryDataClass
import com.calmscient.databinding.CalendarDayLayoutBinding
import com.calmscient.databinding.FragmentJournalEntryBinding
import com.calmscient.utils.getColorCompat
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import kotlin.random.Random
class JournalEntryFragment : Fragment(), CellClickListener {

    private lateinit var binding: FragmentJournalEntryBinding
    private lateinit var adapter: JournalEntryAdapter
    private var selectedDate = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd")
    private val cardDataList = mutableListOf<JournalEntryDataClass>()
    private var adapterPositionToDelete = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(WeeklySummaryFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJournalEntryBinding.inflate(inflater, container, false)
        binding.tvJournalEntry.text = getString(R.string.journal_entry)

        binding.backIcon.setOnClickListener {
            loadFragment(WeeklySummaryFragment())
        }

        // Initialize RecyclerView
        val recyclerView = binding.recyclerViewJournalEntry
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = JournalEntryAdapter(cardDataList)
        recyclerView.adapter = adapter

        // Add the Log.d statement here to check the cardDataList size
        Log.d("JournalEntryFragment", "Card Data List Size: ${cardDataList.size}")

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
                        incrementDateByOne()
                        binding.exSevenCalendar.notifyDateChanged(day.date)
                        oldDate?.let { binding.exSevenCalendar.notifyDateChanged(it) }

                        // Update card views for the new selected date
                        displayCardViewsForSelectedDate()
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
        binding.recyclerViewJournalEntry.layoutManager = LinearLayoutManager(requireContext())
        adapter = JournalEntryAdapter(cardDataList)
        binding.recyclerViewJournalEntry.adapter = adapter

        // Display card views for the initial selected date
        displayCardViewsForSelectedDate()
    }

    fun incrementDateByOne() {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        for (i in 0..6) {
            val calendar: Calendar = GregorianCalendar()
            calendar.add(Calendar.DATE, i)
            val day: String = sdf.format(calendar.time)
            Log.i(ContentValues.TAG, day)
        }
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
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun displayCardViewsForSelectedDate() {
        // Update card data based on the selected date
        cardDataList.clear()

        // Sample data initialization (replace with your actual data source)
        initializeSampleData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeSampleData() {
        // Clear existing card data
        cardDataList.clear()

        // Get the selected date in the format "MM/dd/yyyy"
        val selectedDateString = selectedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))

        // Get the card size for the selected date from the cardSizes array
        val cardCount = cardSizes[selectedDate.dayOfMonth - 1] // Adjust index as needed

        if (cardCount == 0) {
            // Show "No entries" TextView when card count is 0
            binding.tvNoEntry.text = getString(R.string.no_entries)
            binding.tvNoEntry.visibility = View.VISIBLE // Make the TextView visible
        } else {
            binding.tvNoEntry.visibility = View.GONE // Hide the "No entries" TextView
        }

        // Add entries for the selected date based on the calculated card count and descriptions
        for (i in 1..cardCount) {
            val description = getCardDescription(i)

            cardDataList.add(
                JournalEntryDataClass(
                    selectedDateString,
                    description,
                    cardCount
                )
            )
        }

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged()
    }


    override fun onCellClickListener(position: Int) {
        // Handle cell click event here
    }

    companion object {
        // Create a static array to store card sizes for each date
        //private val cardSizes = IntArray(40) // Initialize for 40 days

        /* init {
             // Initialize the card sizes based on your requirements
             val random = Random

             for (i in 0 until 40) {
                 // Generate a random card size between 0 and 5
                 cardSizes[i] = random.nextInt(8)
             }
         }*/

        private val cardSizes =   intArrayOf(4,2,6,3,1,0,8,3,2,4,1,8,0,7,4,2,6,3,1,0,8,3,2,4,1,0,8,7,4,2,6,3,8,1,0,3,2,4,8,1,0,7,8)

    }

    private fun getCardDescription(cardPosition: Int): String {
        return when (cardPosition) {
            1 -> "Had a reasonably good day today"
            2 -> "Starting to get a little depressed"
            3 -> "Had a lot of stress at work today. Didn’t really sleep that well."
            4 -> "Trying to work through a little bit of anxiety. My boss is pressuring me to give a presentation before a large crowd."
            5 -> "I wish I could talk with a good friend about how I feel."
            6 -> "I did terrible on my presentation. My self esteem has dropped."
            7 -> "I have been having  bad thoughts today.I need help."
            8 -> "I am very depressed today. I am barely able to get out of bed."
            else -> "Default Description"
        }
    }

    private fun handleDeleteEntry() {
        val selectedPosition = adapterPositionToDelete // Create a variable to store the selected position
        if (selectedPosition != -1) {
            adapter.removeEntry(selectedPosition) // Remove the entry from the adapter
            adapterPositionToDelete = -1 // Reset the selected position
        }
    }
}