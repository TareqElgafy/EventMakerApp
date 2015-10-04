package com.example.reem.eventmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Reem on 8/24/2015.
 */
public class EventsAvailable extends Activity {

    Button btn_createEvent,btn_viewEvents;
    AutoCompleteTextView autoCompleteTextView;
    ShowSend showSend=new ShowSend();
    TextView tv;
    public static ShowReceive eventDetails=new ShowReceive();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);


        btn_createEvent = (Button) findViewById(R.id.btn_createEvent);
        btn_viewEvents=(Button)findViewById(R.id.btn_show);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoComplete);
        tv=(TextView)findViewById(R.id.textView2);



        ArrayAdapter<EventName> adapter = new ArrayAdapter<EventName>(this,android.R.layout.simple_list_item_1,SignIn.events);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventName selected = (EventName) parent.getAdapter().getItem(position);

                showSend.setUser_id(SignIn.user_id);
                showSend.setId(selected.getId());
                RestAdapter adapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint(SignIn.Endpoint)
                        .build();
                GetAPI api = adapter.create(GetAPI.class);
                api.show_event(showSend,new Callback<ShowReceive>() {
                    @Override
                    public void success(ShowReceive showReceive, Response response) {
                        eventDetails=showReceive;
                        Intent in =new Intent(getApplicationContext(),EventDetails.class);
                        startActivity(in);
                       //    tv.setText(showReceive.getId()+""+showReceive.getUsername()+" "+showReceive.getName()+" "
                       //   +showReceive.getDescription()+" "+showReceive.getLocation()+" "+showReceive.getDate());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(EventsAvailable.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        btn_createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),CreateEvent.class);
                startActivity(in);
            }
        });

        btn_viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in2 = new Intent(getApplicationContext(),listActivity.class);
                startActivity(in2);
            }
        });


    }
}
