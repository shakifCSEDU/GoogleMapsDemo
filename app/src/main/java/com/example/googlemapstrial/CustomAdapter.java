package com.example.googlemapstrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private View contentView;

    public CustomAdapter(Context context) {
        this.context = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null,false);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderView(marker,contentView);
        return contentView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderView(marker,contentView);
        return contentView;
    }

    private void renderView(Marker marker,View contentView){
        String title = "",description = "";

        if(marker != null){
            title = marker.getTitle();
            description = marker.getSnippet();
        }

       TextView titleTextView =  contentView.findViewById(R.id.titleId);
       titleTextView.setText(title);
       TextView descriptionTextView = contentView.findViewById(R.id.descriptionId);
       descriptionTextView.setText(description);
    }

}
