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
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapstrial.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ActivityMapsBinding binding;
    private Toolbar toolbar;
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
        if(item.getItemId() == R.id.normal_mapId){
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else if(item.getItemId() == R.id.hybrid_mapId){
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if(item.getItemId() == R.id.terrain_mapId){
            map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(item.getItemId() == R.id.satellite_mapId){
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if(item.getItemId() == R.id.none_mapId){
            map.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
        else if(item.getItemId()==R.id.custom_mapId){
          try{
            boolean success =map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style));
            if(!success)
                Toast.makeText(getApplicationContext(),"Not found !!",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Found !",Toast.LENGTH_SHORT).show();
          }catch (Resources.NotFoundException e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
          }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng dhaka = new LatLng(23.814861037613515, 90.4097105684943);
        map.addMarker(new MarkerOptions().position(dhaka).title("Marker in Dhaka"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaka,15f));

        map.getUiSettings().setZoomGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);




    }
}