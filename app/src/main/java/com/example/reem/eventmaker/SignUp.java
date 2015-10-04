package com.example.reem.eventmaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Reem on 8/24/2015.
 */
public class SignUp extends Activity {

    EditText et_username,et_email,et_password;
   public static String username;
   private User user= new User() ;
    public static int user_id=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        et_username = (EditText) findViewById(R.id.username);
        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);

    }


    public void Sign_Up(View v) {

            user.setUsername(et_username.getText().toString());
            user.setPassword(et_password.getText().toString());
            user.setEmail(et_email.getText().toString());

       RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(SignIn.Endpoint)
                .build();
        GetAPI api = adapter.create(GetAPI.class);
        api.sign_up(user,new Callback<SignUpGet>() {
            @Override
            public void success(SignUpGet signUpGet, Response response) {
                Toast.makeText(SignUp.this,"success",Toast.LENGTH_SHORT).show();
                user_id=signUpGet.getId();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(SignUp.this,"failure",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

