package com.lex.minute_age

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var txtSelectedDate : TextView? = null
    var tvAgeInMinute : TextView? = null
    var txtAgeInMinute : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinute)
        txtAgeInMinute = findViewById(R.id.txtMinutesNow)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener { _, year, month, day ->

           /*Toast.makeText(this, "Day = $day, month = ${month+1}, year = $year", Toast.LENGTH_SHORT).show()*/

            val selectedDate = "$day/${month+1}/$year"
            visible()
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000

                    val difference = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinute?.text = difference.toString()
                }
            }


        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

    private fun visible() {
        tvSelectedDate?.isVisible = true
        txtSelectedDate?.isVisible = true
        tvAgeInMinute?.isVisible = true
        txtAgeInMinute?.isVisible = true
    }
}