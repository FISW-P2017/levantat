package com.example.fernando.levantat_lt003;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SharedPreferences sharedPref;
    Set<String> nombres;
    Set<String> direcciones;
    int id;
    String nombre = "";
    String direccion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double longitude = 0, latitude = 0;

        Intent intent = getIntent();
        direccion = intent.getStringExtra("direccion");
        nombre = intent.getStringExtra("nombre");

        if(direccion.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Agrega una dirección antes de ingresar al mapa", Toast.LENGTH_LONG)
                    .show();
        }

            Geocoder coder = new Geocoder(this);
            try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(direccion, 50);
                for(Address add : adresses){
                        longitude = add.getLongitude();
                        latitude = add.getLatitude();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(latitude == 0 && longitude == 0)
                Toast.makeText(getApplicationContext(),
                        "La dirección dada no es válida"+direccion, Toast.LENGTH_LONG)
                        .show();
            else {
                LatLng lugar = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(lugar).title(nombre));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 12.0f));
            }
    }
}
