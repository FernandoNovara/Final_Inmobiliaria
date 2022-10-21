package com.example.final_inmobiliaria;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.final_inmobiliaria.databinding.ActivityMainBinding;
import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiClient;
import com.example.final_inmobiliaria.request.ApiRetrofit;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private int bandera;
    private SensorManager sm;
    private LeeSensor ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        iniciarHeader(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.logoutFragment,R.id.inicioFragment,R.id.perfilFragment,R.id.inmuebleFragment,R.id.inquilinoFragment,R.id.contratoFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }


    @Override
    protected void onResume() {
        super.onResume();

        obtenerSensor();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sm.unregisterListener(ls);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void iniciarHeader(NavigationView navigationView){


        ApiClient api= ApiClient.getApi();

        View header = navigationView.getHeaderView(0);
        ImageView avatar = header.findViewById(R.id.ivFotoPerfil);
        TextView nombreProp = header.findViewById(R.id.tvNombrePerfil);
        TextView mailProp = header.findViewById(R.id.tvEmailPerfil);

        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(navigationView.getContext());
        Call<Propietario> propietario = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(sp.getString("token","-1"));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful())
                {
                    Propietario p = response.body();
                    nombreProp.setText(p.getNombre()+ " " + p.getApellido());
                    mailProp.setText(p.getEmail());
                    avatar.setImageResource(p.getAvatar());
                }
                else
                {
                    Log.d("Salida","no mando nada");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });

    }

    /*----------------------------------------------------
        Sensor
    ----------------------------------------------------*/

    public void obtenerSensor()
    {

        ls = new LeeSensor();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (listaSensores.size() > 0)
        {
            sm.registerListener(ls,listaSensores.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
    }

    private class LeeSensor implements SensorEventListener
    {


        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (bandera == 0 && (sensorEvent.values[0] >= 7 || sensorEvent.values[0] <= -7))
            {
               // Uri tel = Uri.parse("tel:"+113);
               //startActivity(new Intent(Intent.ACTION_CALL,tel));
               //bandera = 1;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}