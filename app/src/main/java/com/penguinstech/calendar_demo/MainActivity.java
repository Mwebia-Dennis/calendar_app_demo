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
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.alamkanak.weekview.WeekViewLoader;
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

        WeekView.EventClickListener mEventClickListener = (event, eventRect) -> {

            Log.d("event click", "true");
            Toast.makeText(MainActivity.this, "event clicked", Toast.LENGTH_SHORT).show();
        };
        WeekView.EventLongPressListener mEventLongPressListener = (event, eventRect) -> {

            Log.d("event long click", "true");
            Toast.makeText(MainActivity.this, "event long clicked", Toast.LENGTH_SHORT).show();
        };

        MonthLoader.MonthChangeListener mMonthChangeListener = (newYear, newMonth) -> {
            // Populate data when month changes.
            // The week view has infinite scrolling horizontally. We have to provide the events of a
            // month every time the month changes on the week view.
            return getEvents(newYear, newMonth);
        };

        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView);
        mWeekView.setMonthChangeListener(mMonthChangeListener);
        mWeekView.setOnEventClickListener(mEventClickListener);
        mWeekView.setEventLongPressListener(mEventLongPressListener);
    }


    /**
     *
     * @param newYear: current year after year changes on calendar
     * @param newMonth: the new month, every time the new month changes on the calendar
     * @return list of events of the new month
     *
     * points to note
     *
     * every event should have a unique id
     */
    private List<WeekViewEvent> getEvents(int newYear, int newMonth) {

        //create events and add to calendar based on the new month and new year.
        //this demo data will be duplicated every new month
        HashSet<WeekViewEvent> events = new HashSet<>();
        events.clear();

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = (Calendar) startTime.clone();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, "birthday", startTime, endTime);
        event.setColor(getResources().getColor(R.color.teal_200));
        events.add(event);

        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = (Calendar) startTime.clone();
        startTime1.set(Calendar.HOUR_OF_DAY, 10);
        startTime1.set(Calendar.MINUTE, 30);
        startTime1.set(Calendar.MONTH, newMonth-1);
        startTime1.set(Calendar.YEAR, newYear);
        endTime1.set(Calendar.HOUR_OF_DAY, 11);
        endTime1.set(Calendar.MINUTE, 0);
        endTime1.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event1 = new WeekViewEvent(2, "new event", startTime1, endTime1);
        event1.setColor(getResources().getColor(R.color.pink));
        events.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        Calendar endTime2 = (Calendar) startTime.clone();
        startTime2.set(Calendar.HOUR_OF_DAY, 16);
        startTime2.set(Calendar.MINUTE, 10);
        startTime2.set(Calendar.MONTH, newMonth-1);
        startTime2.set(Calendar.YEAR, newYear);
        endTime2.set(Calendar.HOUR_OF_DAY, 16);
        endTime2.set(Calendar.MINUTE, 59);
        endTime2.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event2 = new WeekViewEvent(3, "event2", startTime2, endTime2);
        event2.setColor(getResources().getColor(R.color.blue));
        events.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = (Calendar) startTime.clone();
        startTime3.set(Calendar.HOUR_OF_DAY, 16);
        startTime3.set(Calendar.MINUTE, 10);
        startTime3.set(Calendar.MONTH, newMonth-1);
        startTime3.set(Calendar.YEAR, newYear);
        endTime3.set(Calendar.HOUR_OF_DAY, 16);
        endTime3.set(Calendar.MINUTE, 59);
        endTime3.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event3 = new WeekViewEvent(4, "event3", startTime3, endTime3);
        event3.setColor(getResources().getColor(R.color.green));
        events.add(event3);

        Calendar startTime4 = Calendar.getInstance();
        Calendar endTime4 = (Calendar) startTime.clone();
        startTime4.set(Calendar.HOUR_OF_DAY, 16);
        startTime4.set(Calendar.MINUTE, 40);
        startTime4.set(Calendar.MONTH, newMonth-1);
        startTime4.set(Calendar.YEAR, newYear);
        endTime4.set(Calendar.HOUR_OF_DAY, 17);
        endTime4.set(Calendar.MINUTE, 30);
        endTime4.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event4 = new WeekViewEvent(5, "event4", startTime4, endTime4);
        event4.setColor(getResources().getColor(R.color.teal_700));
        events.add(event4);
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