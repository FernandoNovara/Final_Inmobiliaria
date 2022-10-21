package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel
{
    private Context contexto;

    public AgregarInmuebleViewModel(@NonNull Application application)
    {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public void AgregarInmueble(Inmueble inmueble)
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Call<Inmueble> dato = ApiRetrofit.getServiceInmobiliaria().AltaInmueble(sp.getString("token","-1"),inmueble);
        dato.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(contexto,"Se agrego correctamente.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(contexto,"No se ha podido agregar.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

            }
        });

        Log.d("Salida","Entro al Agregar");
    }
}