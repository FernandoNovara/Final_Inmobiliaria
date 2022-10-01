package com.example.final_inmobiliaria.ui.Inicio;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class LeerMapa implements OnMapReadyCallback {

    private Context context;
    private GoogleMap googleMap;
    private LocationManager manager;
    static final LatLng INMOBILIARIA = new LatLng(-33.051034, -65.627148);
    static final LatLng SANLUIS = new LatLng(-33.280576, -66.332482);
    static final LatLng ULP = new LatLng(-33.150720, -66.306864);

    public LeerMapa(Context context) {
        this.context = context;
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onMapReady(@NonNull GoogleMap Map) {
        googleMap = Map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA))
                .setTitle("Inmobiliaria La Toma");

        CameraPosition positionInmobiliaria = new CameraPosition.Builder()
                .target(INMOBILIARIA)
                .zoom(20)
                .bearing(0)
                .build();

        CameraUpdate caInmobiliaria = CameraUpdateFactory.newCameraPosition(positionInmobiliaria);
        googleMap.animateCamera(caInmobiliaria);

        PosicionActual();
    }

    public void PosicionActual() {
        FusedLocationProviderClient fl = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fl.getLastLocation().addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        LatLng miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(miUbicacion))
                                .setTitle("Mi Ubicacion");
                    }

                }
            });
        }
        else
        {
            long tiempo = 5000;
            float distancia = 10;

            ListenerPosition listener = new ListenerPosition();

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempo, distancia, listener);

            Task<Location> tl = fl.getLastLocation();

            tl.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null)
                    {
                        LatLng miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(miUbicacion))
                                .setTitle("Mi Ubicacion");
                    }
                }
            });
            manager.removeUpdates(listener);
        }
    }

    private class ListenerPosition implements LocationListener
    {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            //Recibe nueva posicion.
            double lat = location.getLatitude();
            double lon = location.getLongitude();
        }

        @Override
        public void onLocationChanged(@NonNull List<Location> locations) {
            LocationListener.super.onLocationChanged(locations);
            //Recibe una lista de posiciones.
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //LocationListener.super.onStatusChanged(provider, status, extras);
            //Cambio en el estado del proveedor
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            LocationListener.super.onProviderEnabled(provider);

            //El proveedor ha sido conectado
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);

            //El proveedor ha sido desconectado
        }
    }
}
