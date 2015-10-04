package com.example.reem.eventmaker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Reem on 8/24/2015.
 */
public class CreateEvent extends Activity {


    public static String date="",time="",name,desc,location;
    EditText fnameEdit, dnameEdit,lname;
    public static int d,m,y,hr,min;
    public static String notificationMessage;
    RegistrationAdapter adapter;
    RegistrationOpenHelper helper;
    EventSend eventSend = new EventSend();
  //  TextView txt_date,txt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        fnameEdit =(EditText)findViewById(R.id.name);
        dnameEdit =(EditText)findViewById(R.id.desc);
        lname=(EditText)findViewById(R.id.et_lname);
        adapter = new RegistrationAdapter(this);
     //   txt_date=(TextView)findViewById(R.id.txt_date);
     //   txt_time=(TextView)findViewById(R.id.txt_time);

     //   txt_date.setText(date);
     //   txt_time.setText(time);


    }
    public void pickDate(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date =day+"-"+month+"-"+year;
            d=day;
            m=month;
            y=year;
        }
    }

    public void pickTime(View v)
    {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "time");
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time=hourOfDay+":"+minute;
            hr=hourOfDay;
            min=minute;

        }
    }

    public void openMap(View v){
        Intent in = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(in);
    }

    public void createEvent(View v)
    {
        name = fnameEdit.getText().toString();
        desc= dnameEdit.getText().toString();
        location=lname.getText().toString();
        notificationMessage = name + " Event starts now";



        Calendar beginTime =Calendar.getInstance();
        beginTime.set(y,m,d,hr,min);
        long bt=  beginTime.getTimeInMillis();

        long val = adapter.insertDetails(name,desc,date,time ,location );


        Intent in = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,in,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC,bt,pendingIntent);



        eventSend.setName(name);
        eventSend.setDescription(desc);
        eventSend.setDate(bt);
        eventSend.setUser_id(SignIn.user_id);
        eventSend.setLocation(location);
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(SignIn.Endpoint)
                .build();
        GetAPI api = adapter.create(GetAPI.class);
        api.send_event(eventSend,new Callback<ErrorMessage>() {
            @Override
            public void success(ErrorMessage errorMessage, Response response) {
                Toast.makeText(CreateEvent.this, "Event created", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),EventsAvailable.class);
                startActivity(in);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(CreateEvent.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();

            }
        });


    }


}