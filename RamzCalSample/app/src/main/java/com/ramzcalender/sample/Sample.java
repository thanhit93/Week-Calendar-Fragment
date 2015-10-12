package com.ramzcalender.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.ramzcalender.RWeekCalender;
import com.ramzcalender.listener.CalenderListener;

import org.joda.time.LocalDateTime;

import java.util.Calendar;


/**
 * Created by rameshvoltella on 11/10/15.
 */

//## Licence of Date picker using in this sample

/**
 * Copyright 2014 Paul Stöhr
Copyright 2013 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

public class Sample extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    RWeekCalender rCaldroidFragment;
    TextView mDateSelectedTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        mDateSelectedTv=(TextView)findViewById(R.id.txt_date);

        rCaldroidFragment=new RWeekCalender();

        Bundle args = new Bundle();

       /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
        args.putString(RWeekCalender.PACKAGENAME,getApplicationContext().getPackageName());

       /* IMPORTANT: Customization for the calender commenting or un commenting any of the attribute below will reflect change in calender*/

//---------------------------------------------------------------------------------------------------------------------//

//      args.putInt(RWeekCalender.CALENDER_BACKGROUND, ContextCompat.getColor(this,R.color.md_pink_700));//set background color to calender

        args.putString(RWeekCalender.DATE_SELECTOR_BACKGROUND,"bg_select");//set background to the selected dates

        args.putInt(RWeekCalender.WEEKCOUNT,1000);//add N weeks from the current week (53 or 52 week is one year)

//        args.putString(RWeekCalender.NOW_BACKGROUND,"bg_now");//set background to nowView

//        args.putInt(RWeekCalender.CURRENT_DATE_BACKGROUND,ContextCompat.getColor(this,R.color.md_black_1000));//set color to the currentdate

//        args.putInt(RWeekCalender.PRIMARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_white_1000));//Set color to the primary views (Month name and dates)

//        args.putInt(RWeekCalender.SECONDARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_green_500));//Set color to the secondary views (now view and week names)

//---------------------------------------------------------------------------------------------------------------------//

        rCaldroidFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.container, rCaldroidFragment);
        t.commit();

        CalenderListener listener=new CalenderListener() {
            @Override
            public void onSelectPicker() {

               //User can use any type of pickers here the below picker is only Just a example

            DatePickerDialog.newInstance(Sample.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");


            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {

                //callback when a date is selcted

                mDateSelectedTv.setText(""+mSelectedDate.getDayOfMonth()+"-"+mSelectedDate.getMonthOfYear()+"-"+mSelectedDate.getYear());
            }
        };

        //setting the listener
        rCaldroidFragment.setCalenderListener(listener);

    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

//This is the call back from picker used in the sample you can use custom or any other picker

        //IMPORTANT: get the year,month and date from picker you using and call setDateWeek method
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, monthOfYear, dayOfMonth);
        rCaldroidFragment.setDateWeek(calendar);//Sets the selected date from Picker


    }
}