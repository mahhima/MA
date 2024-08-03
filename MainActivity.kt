package com.example.timeconvertor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val inputTime = findViewById<EditText>(R.id.inputTime)
        val buttonCSTtoIST = findViewById<Button>(R.id.buttonCSTtoIST)
        val buttonISTtoCST = findViewById<Button>(R.id.buttonISTtoCST)

        // Define date format
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Button click listeners
        buttonCSTtoIST.setOnClickListener {
            val cstTime = inputTime.text.toString()
            try {
                val cstCalendar = Calendar.getInstance().apply {
                    timeZone = TimeZone.getTimeZone("America/Chicago")
                    time = timeFormat.parse(cstTime)!!
                }

                // Add 10 hours and 30 minutes to CST time to convert to IST
                cstCalendar.add(Calendar.HOUR_OF_DAY, 10)
                cstCalendar.add(Calendar.MINUTE, 30)

                val istTime = timeFormat.format(cstCalendar.time)
                Toast.makeText(this, "IST Time: $istTime", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid CST Time Format", Toast.LENGTH_SHORT).show()
            }
        }

        buttonISTtoCST.setOnClickListener {
            val istTime = inputTime.text.toString()
            try {
                val istCalendar = Calendar.getInstance().apply {
                    timeZone = TimeZone.getTimeZone("Asia/Kolkata")
                    time = timeFormat.parse(istTime)!!
                }

                // Subtract 10 hours and 30 minutes from IST time to convert to CST
                istCalendar.add(Calendar.HOUR_OF_DAY, -10)
                istCalendar.add(Calendar.MINUTE, -30)

                val cstTime = timeFormat.format(istCalendar.time)
                Toast.makeText(this, "CST Time: $cstTime", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid IST Time Format", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
