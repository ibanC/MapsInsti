package com.example.dm2.mapsinsti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private int vistaActual;
    private Button animar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        animar=(Button)findViewById(R.id.btnAnimar);
        animar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animarInsti();
            }
        });
    }

    private void animarInsti() {
        LatLng insti=new LatLng(42.8370772,-2.6792914);
        CameraPosition camPos=new CameraPosition.Builder().target(insti).zoom(17).bearing(45).build();

        CameraUpdate camUpd3= CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa=googleMap;
        vistaActual=GoogleMap.MAP_TYPE_SATELLITE;
        mapa.setMapType(vistaActual);
        mapa.getUiSettings().setZoomControlsEnabled(true);
    }
    public void cambiarVista(View v)
    {
        if(vistaActual==GoogleMap.MAP_TYPE_SATELLITE)
        {
            vistaActual=GoogleMap.MAP_TYPE_NORMAL;
            mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else
        {
            vistaActual=GoogleMap.MAP_TYPE_SATELLITE;
            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

    }
}
