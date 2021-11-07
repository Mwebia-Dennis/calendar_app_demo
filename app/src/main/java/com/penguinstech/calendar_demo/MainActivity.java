package com.penguinstech.calendar_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.roomorama.caldroid.CaldroidFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WeekView mWeekView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        init();

        initialise();
    }

    private void init() {

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
//        t.replace(R.id.main_container, caldroidFragment);
        t.commit();


    }

    private void initialise() {

        List<WeekViewEvent> events = getEvents();
        WeekView.EventClickListener mEventClickListener = (event, eventRect) -> {

            Log.d("event click", "true");
        };
//        WeekView.EventLongPressListener mEventLongPressListener = (event, eventRect) -> {
//
//            Log.d("event long click", "true");
//        };

        MonthLoader.MonthChangeListener mMonthChangeListener = (newYear, newMonth) -> {
            // Populate the week view with some events.
            return events;
        };
        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView);
        mWeekView.setMonthChangeListener(mMonthChangeListener);
        mWeekView.setOnEventClickListener(mEventClickListener);
        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
//        mWeekView.setEventLongPressListener(mEventLongPressListener);
    }



    private List<WeekViewEvent> getEvents() {

        HashSet<WeekViewEvent> events = new HashSet<>();
        events.clear();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(new Date().toString());
            Calendar startTime = Calendar.getInstance();
            startTime.setTime(date);

            date = formatter.parse(String.valueOf(new Date(Calendar.getInstance().getTimeInMillis() + (10 * 60 * 1000))));
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(date);
            events.add(new WeekViewEvent(1, "birthday", "Home", startTime, endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        events.add(new WeekViewEvent(1, "birthday", 2021, 11,7, 11, 0,
//                2021, 11, 7, 12, 30 ));
//        events.add(new WeekViewEvent(2, "new event", 2021, 11,7, 13, 0,
//                2021, 11, 7, 13, 30 ));
//        events.add(new WeekViewEvent(3, "new event", 2021, 11,8, 13, 0,
//                2021, 11, 8, 13, 30 ));
//        events.add(new WeekViewEvent(4, "new event", 2021, 11,9, 16, 0,
//                2021, 11, 9, 16, 30 ));

        return new ArrayList<>(events);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.today_menu) {
            mWeekView.setNumberOfVisibleDays(1);
            mWeekView.setTextSize(getTextSize(false));
            mWeekView.goToToday();
        }else if (item.getItemId() == R.id._1_day_menu) {
            mWeekView.setNumberOfVisibleDays(1);
            mWeekView.setTextSize(getTextSize(false));
        } else if (item.getItemId() == R.id._3_day_menu) {
            mWeekView.setNumberOfVisibleDays(3);
            mWeekView.setTextSize(getTextSize(false));
        }else if (item.getItemId() == R.id._7_day_menu) {
            mWeekView.setTextSize(getTextSize(true));
            mWeekView.setNumberOfVisibleDays(7);
        }

        return super.onOptionsItemSelected(item);
    }

    private int getTextSize(boolean is7Days) {
        return is7Days?20:24;
    }
}