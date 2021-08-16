package com.example.googlemapstrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapstrial.databinding.ActivityMapsBinding;
import com.google.android.material.snackbar.Snackbar;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap map;
    private ActivityMapsBinding binding;
    private Toolbar toolbar;
    private TypeAndStyle typeAndStyle = new TypeAndStyle(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = (Toolbar)findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.map_types_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        typeAndStyle.setStyle(item,map);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng dhaka = new LatLng(23.814861037613515, 90.4097105684943);
        Marker dhakaMarker =   map.addMarker(new MarkerOptions().position(dhaka).title("Marker in Dhaka"));
        dhakaMarker.setDraggable(true);

        map.moveCamera(CameraUpdateFactory.newLatLng(dhaka));
        new Thread(()-> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                map.animateCamera(CameraUpdateFactory.newCameraPosition(typeAndStyle.cameraAndViewPort(dhaka)), 1000, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(getApplicationContext(),"Finish",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(),"Cancle Animation",Toast.LENGTH_SHORT).show();
                    }
                });

            });
        }).start();

        map.getUiSettings().setZoomGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        onClickEventMap();
        onLongClickMap();
        map.setOnMarkerDragListener(this);
    }

    private void onLongClickMap() {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Snackbar.make(findViewById(R.id.linear_layoutId),getString(R.string.click),Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void onClickEventMap() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(getApplicationContext(),"Your click Location -> LON :"+latLng.longitude+"\nLAT"+latLng.latitude,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMarkerDragStart( Marker marker) {
        Toast.makeText(getApplicationContext(),"Start..",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(getApplicationContext(),"Dragging..",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
    }


}