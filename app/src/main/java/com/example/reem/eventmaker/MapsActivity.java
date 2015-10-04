package com.example.reem.eventmaker;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private static final float DefaultZoom=15;
    List<Marker> myList= new ArrayList<Marker> ();
    public LatLng loct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }
    private void gotoloction (double lat,double lng,float zoom){
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mMap.moveCamera(update);
    }

    public void geolocate(View v) throws IOException {
        hidesoftkeyboard(v);
        EditText ed= (EditText) findViewById(R.id.edit1);
        try{
            String loction = ed.getText().toString();
            Geocoder go = new Geocoder(this);
            List<Address> list = go.getFromLocationName(loction, 1);
            Address add = list.get(0);
            String locality = add.getLocality();
            double lat = add.getLatitude();
            double lng = add.getLongitude();
            gotoloction(lat, lng, DefaultZoom);
            Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).draggable(true));
            m.setDraggable(true);
            loct = m.getPosition();
           // myList.add(m);
        }
        catch (Exception e){
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(30.044420, 31.235712))
                    .draggable(true));
            loct = m.getPosition();
        }

    }

    private void hidesoftkeyboard(View v) {
        InputMethodManager mm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mm.hideSoftInputFromWindow(v.getWindowToken(),0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
// Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
// Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
// Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
    //    for (int i=0;i<myList.size();i++)
    //    {
    //        myList.get(i);

    //    }
    }

    public void Return(View v)
    {
        Intent in = new Intent(getApplicationContext(),CreateEvent.class);
        startActivity(in);
    }

}