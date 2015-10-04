package com.example.reem.eventmaker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class EventDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
    }


public void AttendEvent(View v){
    ShowSend ss = new ShowSend();
    ss.setUser_id(1);
    ss.setId(7);

    RestAdapter adapter = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(SignIn.Endpoint)
            .build();
    GetAPI api = adapter.create(GetAPI.class);
    api.attend_event(ss,new retrofit.Callback<ErrorMessage>() {
        @Override
        public void success(ErrorMessage errorMessage, Response response) {
            Toast.makeText(EventDetails.this, "Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(EventDetails.this, "Failure", Toast.LENGTH_SHORT).show();

        }
    });
}
}
