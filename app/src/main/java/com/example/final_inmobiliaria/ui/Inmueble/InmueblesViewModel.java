package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel
{
    private Context contexto;
    private MutableLiveData<ArrayList<Inmueble>> inmuebleMutable;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebleMutable()
    {
        if (inmuebleMutable == null)
        {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public void cargarInmuebles()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Call<ArrayList<Inmueble>> Inmuebles = ApiRetrofit.getServiceInmobiliaria().obtenerInmuebles(sp.getString("token","-1"));
        Inmuebles.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if (response.isSuccessful())
                {
                    ArrayList lista = response.body();
                    inmuebleMutable.setValue(lista);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {

            }
        });
        //ApiClient api = ApiClient.getApi();
        //this.inmuebleMutable.setValue(api.obtnerPropiedades());
    }
}