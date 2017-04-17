package com.example.android.reportbee;

/**
 * Created by Anurag10 on 4/11/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class CalenderUI extends AppCompatActivity {
    final Calendar c = Calendar.getInstance();
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        DatePicker datePickerDialog = (DatePicker) findViewById(R.id.simpleDatePicker); // initiate a date picker
        //      datePickerDialog.setMinDate(System.currentTimeMillis() - 1000);
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -60); // subtract 60 days from now
        try {
            datePickerDialog.setMinDate(c.getTimeInMillis());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Calendar d = Calendar.getInstance();
        try {
            datePickerDialog.setMaxDate(d.getTimeInMillis());
        } catch (Throwable e) {
            e.printStackTrace();
        }//Set Max Date to Current Date
        datePickerDialog.init(datePickerDialog.getYear(), datePickerDialog.getMonth(),
                datePickerDialog.getDayOfMonth(), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker arg0, int year, int month, int date) {
                        // TODO Auto-generated method stub
                        if ((String.valueOf(date).length() == 1) && (String.valueOf(month).length() != 1)) {
                            selectedDate = "0" + String.valueOf(date) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                        } else if ((String.valueOf(date).length() != 1) && (String.valueOf(month).length() == 1)) {
                            selectedDate = String.valueOf(date) + "-" + "0" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                        } else if ((String.valueOf(date).length() == 1) && (String.valueOf(month).length() == 1)) {
                            selectedDate = "0" + String.valueOf(date) + "-" + "0" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                        } else {
                            selectedDate = String.valueOf(date) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                        }
                        Log.v("Date", selectedDate);
                        Intent intent = new Intent(CalenderUI.this, MarkAttendence.class);
                        startActivity(intent);
                    }
                });
    }
}