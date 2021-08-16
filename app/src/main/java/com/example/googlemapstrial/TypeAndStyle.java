package com.example.googlemapstrial;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.googlemapstrial.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

public class TypeAndStyle {
    private Context context;
    public TypeAndStyle(Context context){
        this.context = context;
    }
    public void setStyle(MenuItem item,GoogleMap map){
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
                boolean success =map.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.style));
                if(!success)
                    Toast.makeText(context,"Not found !!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context,"Found !",Toast.LENGTH_SHORT).show();
            }catch (Resources.NotFoundException e){
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    public CameraPosition cameraAndViewPort(LatLng latLng){
        CameraPosition dhaka = CameraPosition.builder().target(latLng).zoom(17f)
                .bearing(0f)
                .tilt(45f).build();
        return dhaka;
    }



}
