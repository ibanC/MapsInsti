package com.example.dm2.mapsinsti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private int vistaActual;
    private Button animar,posInicial;
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

        posInicial=(Button)findViewById(R.id.btnPosInicial);
        posInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng insti=new LatLng(0,0);
                CameraPosition camPos=new CameraPosition.Builder().target(insti).zoom(2).bearing(0).tilt(0).build();

                CameraUpdate camUpd3= CameraUpdateFactory.newCameraPosition(camPos);
                mapa.animateCamera(camUpd3);
            }
        });
    }

    private void animarInsti() {
        LatLng insti=new LatLng(42.837211449,-2.6774750792914);
        CameraPosition camPos=new CameraPosition.Builder().target(insti).zoom(19).bearing(0).tilt(67.5f).build();

        CameraUpdate camUpd3= CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa=googleMap;
        mapa.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Toast.makeText(MainActivity.this,"Lat"+cameraPosition.target.latitude+"lon:"+cameraPosition.target.longitude+" zoom"
                +cameraPosition.zoom+"orientation"+cameraPosition.bearing+"angulo"+cameraPosition.tilt,Toast.LENGTH_LONG).show();
            }
        });
        vistaActual=GoogleMap.MAP_TYPE_SATELLITE;
        mapa.setMapType(vistaActual);
        mapa.addMarker(new MarkerOptions().position(new LatLng(42.837211449,-2.6774750792914)).title("Ciudad Jardin"));
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
