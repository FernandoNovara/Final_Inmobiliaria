package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel
{
    private MutableLiveData<Inmueble> InmuebleMutable;
    private MutableLiveData<Boolean> estadoMutable;
    private Context contexto;
    private int id;

    public DetalleInmuebleViewModel(@NonNull Application application)
    {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<Inmueble> getInmuebleMutable()
    {
        if (InmuebleMutable == null)
        {
            InmuebleMutable = new MutableLiveData<>();
        }
        return InmuebleMutable;
    }

    public LiveData<Boolean> getEstadoMutable()
    {
        if (estadoMutable == null)
        {
            estadoMutable = new MutableLiveData<>();
        }
        return estadoMutable;
    }

    public void cargarInmueble(Bundle bundle)
    {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        this.id = inmueble.getIdInmueble();
        this.InmuebleMutable.setValue(inmueble);
    }

    public void CambiarEstado()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Call<Boolean> estado = ApiRetrofit.getServiceInmobiliaria().CambiarEstado(sp.getString("token","-1"),id);
        estado.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                {
                    Log.d("Salida",response.body().toString());
                    Toast.makeText(contexto,"Se ha cambiado el estado correctamente.",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

    }
}