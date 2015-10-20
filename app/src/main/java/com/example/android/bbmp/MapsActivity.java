package com.example.android.bbmp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;


import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    Toolbar toolbar;
    public static String location=MainActivity.item;
    ImageButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
       toolbar = (Toolbar)  findViewById(R.id.tool_bar);

       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Log.d("","");
        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),CameraActivity.class);
                  startActivity(i);

                }
        });

        setUpMapIfNeeded();
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
        LatLng p =new LatLng(0,0);
               //new LatLng(12.96,77.86);


       switch(MainActivity.item)
        {
            case "Yelahanka":p=new LatLng(13.1139, 77.5983);
                                 break;
            case "Jayanagar":p=new LatLng(12.9250, 77.5950);
                              break;
            case "Dasarahalli":p=new LatLng(13.0987, 77.8389);
                               break;
            case "Rajarajeshwarinagar":p=new LatLng(12.9242199,77.5191194);
                break;
            case "Hebbal":p=new LatLng(13.0400, 77.5900);
                break;
            case "K.R.Puram":p=new LatLng(12.9950, 77.6800);
                break;
            case "Yeshwanthpur":p=new LatLng(13.0285, 77.5462);
                break;
            case "C.V.Ramanagar":p=new LatLng(12.9856, 77.6639);
                break;
            case "Shivajinagar":p=new LatLng(12.9900, 77.6000);
                break;
            case "Rajajinagar":p=new LatLng(12.9700, 77.5700);
                break;
            case "Vijayanagar":p=new LatLng(12.9600, 77.5400);
                break;
            case "Chamrajpet":p=new LatLng(12.9500, 77.5500);
                break;
            case "Chikpet":p=new LatLng(12.9719, 77.5772);
                break;
            case "BTM Layout":p=new LatLng(12.9200, 77.6100);
                break;
            case "Basavangudi":p=new LatLng(12.9400, 77.5700);
                break;


        }
      //  Toast.makeText(getApplicationContext(), "" + MainActivity.pos1, Toast.LENGTH_LONG).show();
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p,13));
        mMap.addMarker(new MarkerOptions().position(p).title("Marker")).setDraggable(true);


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng pos = marker.getPosition();
                Geocoder geocoder;
                List<Address> addresses=null;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
              try {
                  addresses = geocoder.getFromLocation(pos.latitude, pos.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
              }
              catch(IOException e)
              {
                  e.printStackTrace();
              }
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city =    addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                location=""+address+city;
                Toast.makeText(getApplicationContext(), "" + address + city , Toast.LENGTH_LONG).show();

            }
        });


    }

    }
