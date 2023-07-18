package com.calmscient.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.calmscient.R
import com.calmscient.utils.LocaleHelper
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    lateinit var btn: Button
    lateinit var tv_welcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        btn = findViewById(R.id.cng_button)
        tv_welcome = findViewById(R.id.textView)

        btn.setOnClickListener {
            //setLanguage("es")
            greeting()
        }
    }

    fun greeting(){
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        var greeting: String? = null
        if(hour in 12..16){
            greeting = getString(R.string.good_afternoon)
        } else if(hour in 17..20){
            greeting = getString(R.string.good_evening)
        } else if(hour in 21..23){
            greeting = "Good Night";
        } else {
            greeting = getString(R.string.good_morning)
        }
        tv_welcome.setText(greeting)
    }

    fun setLanguage(languageCode: String) {
        LocaleHelper.setLocale(this, languageCode)
        tv_welcome.setText(R.string.welcome_message)
    }

}
