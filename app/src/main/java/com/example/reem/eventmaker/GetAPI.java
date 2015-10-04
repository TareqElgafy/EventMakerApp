package com.example.reem.eventmaker;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.Callback;
import retrofit.http.PUT;

/**
 * Created by Reem on 8/24/2015.
 */
public interface GetAPI {

 @POST("/users.json")
    public void sign_up(@Body User user,Callback<SignUpGet> response);

// @POST("/events.json")
//    public void send_event(@Body EventSend eventSend, Callback<EventReceive> response);

    @POST("/events.json")
    public void send_event(@Body EventSend eventSend, Callback<ErrorMessage> response);

 @POST("/login.json")
    public void sign_in(@Body SignInSend signInSend,Callback<SignInReceive> response);

 @POST("/show.json")
    public void show_event(@Body ShowSend showSend , Callback<ShowReceive>response );

 @POST("/newevents.json")
    public void search_events(@Body UserID userID, Callback<AllEvents> response);

 @POST("/going.json")
    public void attend_event(@Body ShowSend showsend,Callback<ErrorMessage> message);

 @POST("/view.json")
    public void view_attended(@Body UserID userID,Callback<ReceiveArray>receive);

    @POST("/myevents.json")
    public void view_created(@Body UserID userID,Callback<ReceiveArray>receive);


}




