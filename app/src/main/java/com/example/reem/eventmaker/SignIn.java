package com.example.reem.eventmaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/*import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response; */


public class SignIn extends ActionBarActivity{

    Button btn_signUp;
    Button btn_signIn;
    EditText txt_userName,txt_password;
    SignInSend signInSend=new SignInSend();
    TextView textView;
    SharedPreferences sharedpref ;
    public static int user_id;

    public static final String Endpoint = "https://mysterious-sierra-7942.herokuapp.com";

   public static ArrayList<EventName> events = new ArrayList<EventName>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signUp=(Button) findViewById(R.id.btn_signUp);
        btn_signIn=(Button) findViewById(R.id.btn_signIn);
        txt_userName=(EditText)findViewById(R.id.txt_userName);
        txt_password=(EditText)findViewById(R.id.txt_password);
        textView=(TextView)findViewById(R.id.textView);


        // check if username and password are previously stored
        sharedpref = getSharedPreferences("MyData", MODE_PRIVATE);
       String name=  sharedpref.getString("userName","");
        String password = sharedpref.getString("password","");

        if (name !="" && password != "") // it is not the first time for the user to login
        {
            /* it will get username and password form the SharedPreferences and complete
            the remain steps */
            signInSend.setUsername(name);
            signInSend.setPassword(password);
            RestAdapter adapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(Endpoint)
                    .build();
            GetAPI api = adapter.create(GetAPI.class);
            api.sign_in(signInSend,new Callback<SignInReceive>() {
                @Override
                public void success(SignInReceive signInReceive, Response response) {
                    if(signInReceive.getUsername()!=null) {
                        user_id=signInReceive.getId();
                        UserID userID = new UserID();
                        userID.setUser_id(user_id);
                        RestAdapter adapter = new RestAdapter.Builder()
                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                .setEndpoint(Endpoint)
                                .build();
                        GetAPI api = adapter.create(GetAPI.class);
                        api.search_events(userID,new Callback<AllEvents>() {
                            @Override
                            public void success(AllEvents allEvents, Response response) {
                                //  textView.setText(allEvents.getEvents().size()+" "+ allEvents.getEvents().get(0).getName());
                                events=allEvents.getEvents();

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(SignIn.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                    else {
                        Toast.makeText(SignIn.this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(SignIn.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();

                }
            });

            Intent in = new Intent(getApplicationContext(), EventsAvailable.class);
            startActivity(in);
        }



        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInSend.setUsername(txt_userName.getText().toString());
                signInSend.setPassword(txt_password.getText().toString());
                RestAdapter adapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint(Endpoint)
                        .build();
                GetAPI api = adapter.create(GetAPI.class);
               api.sign_in(signInSend,new Callback<SignInReceive>() {
                   @Override
                   public void success(SignInReceive signInReceive, Response response) {
                       if(signInReceive.getUsername()!=null) {
                           user_id=signInReceive.getId();
                           UserID userID = new UserID();
                           userID.setUser_id(user_id);
                           RestAdapter adapter = new RestAdapter.Builder()
                                   .setLogLevel(RestAdapter.LogLevel.FULL)
                                   .setEndpoint(Endpoint)
                                   .build();
                           GetAPI api = adapter.create(GetAPI.class);
                           api.search_events(userID,new Callback<AllEvents>() {
                               @Override
                               public void success(AllEvents allEvents, Response response) {
                                   //  textView.setText(allEvents.getEvents().size()+" "+ allEvents.getEvents().get(0).getName());
                                  events=allEvents.getEvents();

                               }

                               @Override
                               public void failure(RetrofitError error) {
                                   Toast.makeText(SignIn.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();

                               }
                           });
                      /*     UserID attended_id = new UserID();
                           attended_id.setUser_id(3);
                           RestAdapter adapter2 = new RestAdapter.Builder()
                                   .setLogLevel(RestAdapter.LogLevel.FULL)
                                   .setEndpoint(Endpoint)
                                   .build();
                           GetAPI api2 = adapter2.create(GetAPI.class);
                           api2.view_attended(attended_id,new Callback<ReceiveArray>() {
                               @Override
                               public void success(ReceiveArray receiveArray, Response response) {


                               }

                               @Override
                               public void failure(RetrofitError error) {
                                   Toast.makeText(SignIn.this,"Failed to load events",Toast.LENGTH_SHORT).show();


                               }
                           });

                           RestAdapter adapter3 = new RestAdapter.Builder()
                                   .setLogLevel(RestAdapter.LogLevel.FULL)
                                   .setEndpoint(Endpoint)
                                   .build();
                           GetAPI api3 = adapter3.create(GetAPI.class);
                           api3.view_created(attended_id,new Callback<ReceiveArray>() {
                               @Override
                               public void success(ReceiveArray receiveArray, Response response) {
                                   textView.setText(receiveArray.getEvents_array().size());

                               }

                               @Override
                               public void failure(RetrofitError error) {
                                   Toast.makeText(SignIn.this,"Failed to load events",Toast.LENGTH_SHORT).show();


                               }
                           });  */


                           // store the username and password for the next times
                      SharedPreferences.Editor editor =   sharedpref.edit();
                           editor.putString("userName",txt_userName.getText().toString());
                           editor.putString("password",txt_password.getText().toString());
                           editor.commit();



                           Intent in = new Intent(getApplicationContext(), EventsAvailable.class);
                           startActivity(in);
                        //   textView.setText(signInReceive.getId() + " " + signInReceive.getUsername()
                         //          + " " + signInReceive.getPassword() + " " + signInReceive.getEmail());
                       }
                       else {
                           Toast.makeText(SignIn.this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void failure(RetrofitError error) {
                       Toast.makeText(SignIn.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();

                   }
               });
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getApplicationContext(),SignUp.class);
                startActivity(in2);

            }
        });
    }



}
